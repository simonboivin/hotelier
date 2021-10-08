import { Component, OnInit } from '@angular/core';
import { ClientEntity } from '../classes/entities/client-entity';
import { ClientService } from '../services/client.service';

@Component( {
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
} )
export class ClientsComponent implements OnInit {

  clientsList: ClientEntity[] = [];


  constructor( private clientService: ClientService ) { }

  ngOnInit (): void {
    this.loadData();
  }

  loadData (): void {
    this.clientService.getAllClients().subscribe(
      data => {
        this.clientsList = data;
        console.log( data );
      }
    );
  }

}
