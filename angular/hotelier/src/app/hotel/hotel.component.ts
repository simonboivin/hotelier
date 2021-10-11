import { Component, OnInit, ViewChild } from '@angular/core';
import { HotelEntity } from '../classes/entities/hotel-entity';
import { HotelService } from '../services/hotel.service';
import { faAt, faEdit, faHotel, faPhone, faPlusCircle, faStar, faSync, faTrash } from '@fortawesome/free-solid-svg-icons';

@Component( {
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
} )
export class HotelComponent implements OnInit {

  hotelList: HotelEntity[] = [];
  hotel: HotelEntity = new HotelEntity();

  modalTitre: string = "N/A";


  @ViewChild( 'closeModalButton' ) closeButtonElement: any;


  // Icônes FontAwesome
  faAt = faAt;
  faEdit = faEdit;
  faHotel = faHotel;
  faPhone = faPhone;
  faPlusCircle = faPlusCircle;
  faStar = faStar;
  faSync = faSync;
  faTrash = faTrash;


  constructor( private hotelService: HotelService ) { }


  ngOnInit (): void {
    this.refreshView();
  }

  refreshView (): void {
    this.hotelService.getAllHotels().subscribe( data => {
      this.hotelList = data;
    } );
  }

  add (): void {
    this.hotel = new HotelEntity();
    this.hotel.etoiles = 0;
    this.modalTitre = "Ajouter un nouvel Hôtel";
  }

  edit ( id: number ): void {

    this.hotelService.getHotelById( id ).subscribe( data => {
      this.hotel = data;
      this.modalTitre = "Edition: " + this.hotel.nom;
    } );


  }

  submitForm (): void {
    if ( this.hotel.id == undefined ) { //Ajout
         this.hotelService.addHotel(this.hotel).subscribe( data => {
        console.log(data);
        this.closeButtonElement.nativeElement.click();
        this.refreshView();
      });
    } else { //Edition
      this.hotelService.editHotel(this.hotel).subscribe( data => {
        console.log(data);
        this.closeButtonElement.nativeElement.click();
        this.refreshView();
      });
    }

  }

  delete ( hotelToDelete: HotelEntity ) {
    if ( confirm( "Voules-vous supprimer l'hôtel?" ) ) {
      console.log( "Suppression de l'hotel" );
      this.hotelService.deleteHotel( hotelToDelete ).subscribe( data => {
        console.log( data );
        this.refreshView();
      } );

    }
  }

}
