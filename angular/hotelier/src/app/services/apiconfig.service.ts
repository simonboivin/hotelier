import { Injectable } from '@angular/core';

@Injectable( {
  providedIn: 'root'
} )
export class ApiconfigService {

  constructor() { }




  getToken (): string {
    if ( sessionStorage.getItem( "connectedUserToken" ) != null ) {
      return sessionStorage.getItem( "connectedUserToken" )!;
    } else {
      return "";
    }
  }

  getHttpOptions (): {headers: { Authorization: string; }; } {
    return { 'headers': { 'Authorization': this.getToken() } };
  }

}
