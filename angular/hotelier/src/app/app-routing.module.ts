import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientsComponent } from './clients/clients.component';
import { AuthGuard } from './guards/auth.guard';
import { HotelComponent } from './hotel/hotel.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ReservationsComponent } from './reservations/reservations.component';

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "logout", component: LogoutComponent },
  { path: "clients", component: ClientsComponent, canActivate: [AuthGuard] },
  { path: "hotel", component: HotelComponent, canActivate: [AuthGuard] },
  { path: "resa", component: ReservationsComponent, canActivate: [AuthGuard] }

];

@NgModule( {
  imports: [RouterModule.forRoot( routes )],
  exports: [RouterModule]
} )
export class AppRoutingModule { }
