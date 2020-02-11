import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FlightComponent } from './components/admin/flight/flight.component';
import { FlightResolverService } from './services/admin/flight-resolver.service';
import { CreateFlightComponent } from './components/admin/create-flight/create-flight.component';
import { HomeComponent } from './components/user/home/home.component';
import { NoConnectionComponent } from './components/user/no-connection/no-connection.component';
import { CreateFlightCanDeactivateGuardService } from './components/admin/create-flight/creat-flight-deactivate-guard.service';
import { LoginComponent } from './components/user/login/login.component';
import { RegisterComponent } from './components/user/register/register.component';
import { UserLoginComponent } from './components/user/user-login/user-login.component';
import { CustomerRegisterComponent } from './components/user/customer-register/customer-register.component';
import { BookingdetailsComponent } from './components/user/bookingdetails/bookingdetails.component';
import { BookingComponent } from './components/user/booking/booking.component';
import { AccessDeniedComponent } from './components/access-denied/access-denied.component';
import { AdminGuardService } from './services/admin-authentication-guard.service.ts.service';
import { UserAuthorizationService } from './services/user-authorization.service';
import { ScheduleFlightComponent } from './components/admin/schedule-flight/schedule-flight.component';
import { ViewBookingComponent } from './components/user/view-booking/view-booking.component';
import { TicketsComponent } from './components/user/tickets/tickets.component';



const routes: Routes = [
  {path:'403',component:AccessDeniedComponent},
  {path:'admin/flight/schedule-flight',component:ScheduleFlightComponent},
  {path:'user/booking/new/:flightId', component: BookingComponent,
  canActivate:[UserAuthorizationService] },
  {
    path:"user/booking/ticket/:flightId/:pnr", component : TicketsComponent, canActivate:[UserAuthorizationService]
  },
  {path:'user/booking/view', component:ViewBookingComponent,canActivate:[UserAuthorizationService]},
  { path: 'user/booking', component: BookingdetailsComponent, canActivate:[UserAuthorizationService] },
  { path: 'login', component: UserLoginComponent },
  { path: 'register', component: CustomerRegisterComponent },
  {
    path: 'admin/flight/all',
    component : FlightComponent,
    resolve : {flightList : FlightResolverService},
    canActivate : [AdminGuardService],
  },
  {
    path: 'admin/flight/create-update/:flightid',
    component : CreateFlightComponent,
    canActivate : [AdminGuardService],
  },

  {
    path : '',
    component : HomeComponent
  },
  {
    path : 'no-connection',
    component : NoConnectionComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes,
  {
    onSameUrlNavigation : 'reload'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
