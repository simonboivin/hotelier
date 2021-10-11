import { Component, OnInit, ViewChild } from '@angular/core';
import { faAt, faCheckCircle, faCogs, faEdit, faExclamationTriangle, faHotel, faPhone, faPlusCircle, faStar, faSync, faTrash } from '@fortawesome/free-solid-svg-icons';
import { ClientEntity } from '../classes/entities/client-entity';
import { HotelEntity } from '../classes/entities/hotel-entity';
import { ResaEntity } from '../classes/entities/resa-entity';
import { ClientService } from '../services/client.service';
import { HotelService } from '../services/hotel.service';
import { ResaService } from '../services/resa.service';
import { ClientSorter } from '../sorter/client-sorter';
import { HotelSorter } from '../sorter/hotel-sorter';

@Component( {
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
} )
export class ReservationsComponent implements OnInit {

  resaList: ResaEntity[] = [];
  resa: ResaEntity = new ResaEntity();

  clientList: ClientEntity[] = [];

  hotelList: HotelEntity[] = [];

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

  constructor( private resaService: ResaService, private hotelService: HotelService, private clientService: ClientService, private clientSorter: ClientSorter, private hotelSorter: HotelSorter ) { }

  ngOnInit (): void {
    this.refreshView();
  }

  refreshView (): void {
    this.resaService.getAllResa().subscribe( data => {
      this.resaList = data;
    } );
    this.hotelService.getAllHotels().subscribe( data => {
      this.hotelList = data.sort( this.hotelSorter.sorterByNomAsc );
    } );
    this.clientService.getAllClients().subscribe( data => {
      this.clientList = data.sort( this.clientSorter.sorterByNomAsc );
    } );
  }

  submitForm (): void {
    if ( this.resa.id == undefined ) { //ajout
      this.resaService.addResa( this.resa ).subscribe(
        data => {
          console.log( data );
          this.closeButtonElement.nativeElement.click();
          this.refreshView();
          this.successMessageList.push( "Nouvelle réservation #" + data.id + " ajoutée avec succès" );
        }, error => {
          this.errorMessageList.push( "Erreur lors de la création de la nouvelle réservation" );
        }
      );
    } else { //edition
      this.resaService.editResa( this.resa ).subscribe(
        data => {
          console.log( data );
          this.closeButtonElement.nativeElement.click();
          this.refreshView();
          this.successMessageList.push( "Réservation #" + data.id + " éditée avec succès" );
        }, error => {
          this.errorMessageList.push( "Erreur lors de l'édition de la réservation #" + this.resa.id );
        }
      );
    }
  }

  reset (): void {
    if ( this.resa.id == undefined ) { //ajout
      this.resa = new ResaEntity();
    } else { //edition
      this.edit( this.resa.id );
    }
  }

  add (): void {
    this.resa = new ResaEntity();
    this.modalTitre = "Ajouter une réservation";
  }

  edit ( id: number ): void {

    this.resaService.getResaById( id ).subscribe( data => {
      this.resa = data;
      this.modalTitre = "Editer la réservation #" + this.resa.id;
    } );

  }

  delete ( resaToDelete: ResaEntity ): void {
    if ( confirm( "Etes-vous sur de vouloir supprimer la réservation #" + resaToDelete.id! + " ?" ) ) {
      this.resaService.deleteResa( resaToDelete ).subscribe( data => {
        console.log( data );
        this.refreshView();
        this.successMessageList.push( "Réservation #" + resaToDelete.id + " supprimée avec succès" );
      }, error => {
        this.errorMessageList.push( "Erreur lors de la suppression de la réservation #" + resaToDelete.id! );
      } );
    }
  }

  compareHotelFn ( c1: HotelEntity, c2: HotelEntity ): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }
  compareClientFn ( c1: ClientEntity, c2: ClientEntity ): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }


}
