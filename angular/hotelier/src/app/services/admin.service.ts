import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private httpClient : HttpClient) { }

  authentifier( admin : any){
    return this.httpClient.post<any>(environment.urlBack+"login", admin );
  }
  
}
