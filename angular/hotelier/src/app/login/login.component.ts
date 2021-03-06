import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminEntity } from '../classes/entities/admin-entity';
import { AdminService } from '../services/admin.service';

@Component( {
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
} )
export class LoginComponent implements OnInit {


  /*   public newAdmin: any = {
      username: "",
      password: ""
    };
   */

  public newAdmin: AdminEntity = new AdminEntity();

  public erreur: boolean = false;

  constructor( private adminService: AdminService, private router: Router ) { }

  ngOnInit (): void {
  }

  authentification () {
    this.adminService.authentifier( this.newAdmin ).subscribe(
      data => {
        console.log( 'tentative de login' );
        console.log( data );
        if ( data.id > 0 ) {

          sessionStorage.setItem( "connectedUser", JSON.stringify( data ) );
          sessionStorage.setItem( "connectedUserToken", 'Basic ' + btoa( this.newAdmin.username + ':' + this.newAdmin.password ) );
          console.log( data );
          console.log( "Login ok, redirection..." );

          this.router.navigate( ['/clients'] ).then( () => {
            window.location.reload();
          } );
        } else {
          this.erreur = true;
        }
      }, error => {
        console.log( 'Erreur de login' );
        this.erreur = true;
      }
    );
  };

}
