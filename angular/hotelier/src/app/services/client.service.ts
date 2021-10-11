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

  constructor( private httpClient: HttpClient, private apiconfigService: ApiconfigService ) {
  }

  getAllClients (): Observable<ClientEntity[]> {
    return this.httpClient.get<ClientEntity[]>( environment.urlApi + "client/", this.apiconfigService.getHttpOptions() );
  }

  getClientById ( id: number ): Observable<ClientEntity> {
    return this.httpClient.get<ClientEntity>( environment.urlApi + "client/" + id, this.apiconfigService.getHttpOptions() );
  }

  addClient ( newClient : ClientEntity ) : Observable<ClientEntity> {
    return this.httpClient.post<ClientEntity>( environment.urlApi+ "client/", newClient, this.apiconfigService.getHttpOptions() );
  }

  editClient ( updatedClient : ClientEntity ) : Observable<ClientEntity>{
    return this.httpClient.put<ClientEntity>( environment.urlApi + "client/" + updatedClient.id, updatedClient, this.apiconfigService.getHttpOptions() );
  }

deleteClient( clientToDelete : ClientEntity ): Observable<ClientEntity> {
  return this.httpClient.delete<ClientEntity>( environment.urlApi +"client/"+ clientToDelete.id, this.apiconfigService.getHttpOptions() );
}

}
