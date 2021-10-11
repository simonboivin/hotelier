import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ResaEntity } from '../classes/entities/resa-entity';
import { ApiconfigService } from './apiconfig.service';

@Injectable( {
  providedIn: 'root'
} )
export class ResaService {

  constructor( private httpClient: HttpClient, private apiconfigService: ApiconfigService ) { }

  getAllResa (): Observable<ResaEntity[]> {
    return this.httpClient.get<ResaEntity[]>( environment.urlApi + "resa/", this.apiconfigService.getHttpOptions() );
  }

  getResaById ( id: number ): Observable<ResaEntity> {
    return this.httpClient.get<ResaEntity>( environment.urlApi + "resa/" + id, this.apiconfigService.getHttpOptions() );
  }

  getResaByHotelAndClient ( hotelId: number, clientCheche: string ): Observable<ResaEntity[]> {
    let searchCondition: String = "";
    if ( clientCheche.length > 0 ) {
      searchCondition = "?search=" + clientCheche;
    }
    return this.httpClient.get<ResaEntity[]>( environment.urlApi + "resa/filtered/hotel/" + hotelId + searchCondition, this.apiconfigService.getHttpOptions() );
  }

  getResaSearchedByClient ( clientCheche: string ): Observable<ResaEntity[]> {
    let searchCondition: String = "";
    if ( clientCheche.length > 0 ) {
      searchCondition = "?search=" + clientCheche;
    }
    return this.httpClient.get<ResaEntity[]>( environment.urlApi + "resa" + searchCondition, this.apiconfigService.getHttpOptions() );
  }

  addResa ( newResa: ResaEntity ): Observable<ResaEntity> {
    return this.httpClient.post<ResaEntity>( environment.urlApi + 'resa/', newResa, this.apiconfigService.getHttpOptions() );
  }

  editResa ( updatedResa: ResaEntity ): Observable<ResaEntity> {
    return this.httpClient.put<ResaEntity>( environment.urlApi + "resa/" + updatedResa.id, updatedResa, this.apiconfigService.getHttpOptions() );
  }

  deleteResa ( resaToDelete: ResaEntity ): Observable<ResaEntity> {
    return this.httpClient.delete<ResaEntity>( environment.urlApi + "resa/" + resaToDelete.id, this.apiconfigService.getHttpOptions() );
  }

}
