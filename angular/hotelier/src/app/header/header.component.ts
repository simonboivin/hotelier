import { Component, OnInit } from '@angular/core';
import { AdminEntity } from '../classes/entities/admin-entity';
import { AuthGuard } from '../guards/auth.guard';
import { faHSquare } from '@fortawesome/free-solid-svg-icons';

@Component( {
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
} )
export class HeaderComponent implements OnInit {

  userConnected: AdminEntity = new AdminEntity();


  faHSquare = faHSquare;

  constructor( public auth: AuthGuard ) { }

  ngOnInit (): void {
    if ( this.auth.isConnected() ) {
      this.userConnected = JSON.parse( sessionStorage.getItem( "connectedUser" )! );
    }


  }

}
