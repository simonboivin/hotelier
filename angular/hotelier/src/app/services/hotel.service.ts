import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HotelEntity } from '../classes/entities/hotel-entity';
import { ApiconfigService } from './apiconfig.service';

@Injectable( {
  providedIn: 'root'
} )
export class HotelService {

  constructor( private httpClient: HttpClient, private apiconfigService: ApiconfigService ) { }


  getAllHotels (): Observable<HotelEntity[]> {
    return this.httpClient.get<HotelEntity[]>( environment.urlApi + "hotel/", this.apiconfigService.getHttpOptions() );
  }

  addHotel ( newHotel: HotelEntity ): Observable<HotelEntity> {
    return this.httpClient.post<HotelEntity>( environment.urlApi + "hotel/", newHotel, this.apiconfigService.getHttpOptions() );
  }

  editHotel ( updatedHotel: HotelEntity ): Observable<HotelEntity> {
    return this.httpClient.put<HotelEntity>( environment.urlApi + "hotel/" + updatedHotel.id, updatedHotel, this.apiconfigService.getHttpOptions() );
  }


  deleteHotel ( hotelToDelete: HotelEntity ): void {
    this.httpClient.delete( environment.urlApi + "hotel/" + hotelToDelete.id, this.apiconfigService.getHttpOptions() );
  }

}
