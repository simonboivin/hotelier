import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component( {
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
} )
export class LogoutComponent implements OnInit {

  constructor( private router: Router ) { }

  ngOnInit (): void {
    sessionStorage.removeItem( "connectedUser" );
    sessionStorage.removeItem( "connectedUserToken" );

    this.router.navigate( ['login'] ).then( () => {
      window.location.reload();
    } );

  }

}
