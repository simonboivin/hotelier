import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { ClientsComponent } from './clients/clients.component';
import { LogoutComponent } from './logout/logout.component';
import { HotelComponent } from './hotel/hotel.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';


@NgModule( {
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    ClientsComponent,
    LogoutComponent,
    HotelComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
} )
export class AppModule {}
