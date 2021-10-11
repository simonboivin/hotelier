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
