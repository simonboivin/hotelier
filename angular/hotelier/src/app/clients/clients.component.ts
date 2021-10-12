import { Component, OnInit, ViewChild } from '@angular/core';
import { ClientEntity } from '../classes/entities/client-entity';
import { ClientService } from '../services/client.service';
import { faAt, faCheckCircle, faCogs, faEdit, faExclamationTriangle, faHotel, faPhone, faPlusCircle, faStar, faSync, faTrash } from '@fortawesome/free-solid-svg-icons';

@Component( {
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
} )
export class ClientsComponent implements OnInit {

  clientsList: ClientEntity[] = [];
  client: ClientEntity = new ClientEntity();

  modalTitre: string = "N/A";
  errorMessageList: string[] = [];
  successMessageList: string[] = [];

  @ViewChild( 'closeModalButton' ) closeButtonElement: any;


  // Icônes FontAwesome
  faAt = faAt;
  faCheckCircle = faCheckCircle;
  faCogs = faCogs;
  faEdit = faEdit;
  faExclamationTriangle = faExclamationTriangle;
  faHotel = faHotel;
  faPhone = faPhone;
  faPlusCircle = faPlusCircle;
  faStar = faStar;
  faSync = faSync;
  faTrash = faTrash;

  constructor( private clientService: ClientService ) { }

  ngOnInit (): void {
    this.refreshView();
  }

  refreshView (): void {
    this.clientService.getAllClients().subscribe(
      data => {
        this.clientsList = data;
        console.log( data );
      }
    );
  }

  add (): void {
    this.client = new ClientEntity();
    this.modalTitre = "Ajout d'un nouveau client";
    this.errorMessageList = [];
    this.successMessageList = [];
  }

  edit ( id: number ): void {
    this.clientService.getClientById( id ).subscribe( data => {
      this.client = data;
      this.modalTitre = "Edition du client " + data.id;
      this.errorMessageList = [];
      this.successMessageList = [];
    } );
  }

  submitForm (): void {
    if ( this.client.id == undefined ) { //Ajout
      this.clientService.addClient( this.client ).subscribe(
        data => {
          console.log( data );
          this.closeButtonElement.nativeElement.click();
          this.refreshView();
          this.successMessageList.push( "Nouveau client #" + data.id + " " + data.nom + " ajouté avec succès" );
        }, error => {

          this.errorMessageList.push( "Erreur lors de la création du nouveau client: " + JSON.stringify( error.error ) );
        }
      );
    } else { //Edition
      this.clientService.editClient( this.client ).subscribe(
        data => {
          console.log( data );
          this.closeButtonElement.nativeElement.click();
          this.refreshView();
          this.successMessageList.push( "Client #" + data.id + " " + data.nom + " édité avec succès" );
        }, error => {
          this.errorMessageList.push( "Erreur lors de l'édition du client #" + this.client.id );
        }
      );
    }
  }

  reset (): void {
    if ( this.client.id == undefined ) { //Ajout
      this.client = new ClientEntity();
    } else { //Edition
      this.edit( this.client.id! );
    }
  }

  delete ( clientToDelete: ClientEntity ) {
    if ( confirm( "Etes-vous sur de vouloir supprimer le(la) client(e) #" + clientToDelete.id! + " " + clientToDelete.nom! + " ?" ) ) {
      this.clientService.deleteClient( clientToDelete ).subscribe( data => {
        console.log( data );
        this.refreshView();
        this.successMessageList.push( "Client #" + clientToDelete.id + " " + clientToDelete.nom + " supprimé avec succès" );
      }, error => {
        this.errorMessageList.push( "Erreur lors de la suppression du client #" + clientToDelete.id! + " " + clientToDelete.nom! );
      } );
    }
  }


}
