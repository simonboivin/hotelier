import { Component, OnInit } from '@angular/core';
import { HotelEntity } from '../classes/entities/hotel-entity';
import { HotelService } from '../services/hotel.service';
import { faAt, faHotel, faPhone, faStar } from '@fortawesome/free-solid-svg-icons';

@Component( {
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
} )
export class HotelComponent implements OnInit {

  hotelList: HotelEntity[] = [];

  // Icônes FontAwesome
  faAt = faAt;
  faHotel = faHotel;
  faPhone = faPhone;
  faStar = faStar;

  constructor( private hotelService: HotelService ) { }


  ngOnInit (): void {
    this.refreshView();
  }

  refreshView (): void {
    this.hotelService.getAllHotels().subscribe( data => {
      this.hotelList = data;
    } );
  }

}
