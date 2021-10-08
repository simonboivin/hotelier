import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../services/admin.service';

@Component( {
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
} )
export class LoginComponent implements OnInit {


  public newAdmin: any = {
    username: "",
    password: ""
  };



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
          sessionStorage.setItem( "connectedUser", data );
          console.log( "Login ok, redirection..." );
          this.router.navigate( [''] );
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
