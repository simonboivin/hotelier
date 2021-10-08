import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ClientEntity } from '../classes/entities/client-entity';
import { ApiconfigService } from './apiconfig.service';


@Injectable( {
  providedIn: 'root'
} )
export class ClientService {

  constructor( private httpClient: HttpClient, private apiconfigService : ApiconfigService ) {
  }

  getAllClients (): Observable<ClientEntity[]> {
    return this.httpClient.get<ClientEntity[]>( environment.urlApi + "client/", this.apiconfigService.getHttpOptions());
  }

}
