import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {NgModel, FormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FlightService } from './services/admin/flight.service';
import { FlightComponent } from './components/admin/flight/flight.component';
import { FlightResolverService } from './services/admin/flight-resolver.service';
import { CreateFlightComponent } from './components/admin/create-flight/create-flight.component';
import { AirlinesService } from './services/admin/airlines.service';
import { AirportService } from './services/admin/airport.service';
import { ReactiveFormsModule } from '@angular/forms';
import { NavbarComponent } from './components/navbar/navbar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DataTablesModule } from 'angular-datatables';
import {MatFormFieldModule} from '@angular/material/form-field';
import { HomeComponent } from './components/user/home/home.component';
import {NgxLoadingModule,ngxLoadingAnimationTypes} from 'ngx-loading';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ToastrModule} from 'ngx-toastr';
import { RouterModule } from '@angular/router';
import { NoConnectionComponent } from './components/user/no-connection/no-connection.component';
import { ScheduleFlightComponent } from './components/admin/schedule-flight/schedule-flight.component';
import { LoginComponent } from './components/user/login/login.component';
import { RegisterComponent } from './components/user/register/register.component';
import { UserLoginComponent } from './components/user/user-login/user-login.component';
import { UserLoginServiceModule } from './components/user/user-login/login.service';
import { CustomerRegisterServiceModule } from './components/user/customer-register/register.service';
import { CustomerRegisterComponent } from './components/user/customer-register/customer-register.component';
import { BookingdetailsService } from './services/user/bookingdetails.service';
import { BookingdetailsComponent } from './components/user/bookingdetails/bookingdetails.component';
import { BookingComponent } from './components/user/booking/booking.component';
import {MatStepperModule} from '@angular/material/stepper';
import { MatSliderModule } from '@angular/material/slider'
import {MatButtonModule} from '@angular/material/button';
import { AccessDeniedComponent } from './components/access-denied/access-denied.component';
import { AdminGuardService } from './services/admin-authentication-guard.service.ts.service';
import { ConfirmEqualValidatorDirective } from './services/user/confirm-equal-password.service';
import { UserAuthorizationService } from './services/user-authorization.service';
import { BookingService } from './services/user/booking.service';
import { ViewBookingComponent } from './components/user/view-booking/view-booking.component';
import { TicketsComponent } from './components/user/tickets/tickets.component';



@NgModule({
  declarations: [
    AppComponent,
    FlightComponent,
    CreateFlightComponent,
    NavbarComponent,
    HomeComponent,
    NoConnectionComponent,
    ScheduleFlightComponent,
    LoginComponent,
    RegisterComponent,
    UserLoginComponent,
    CustomerRegisterComponent,
    BookingdetailsComponent,
    BookingComponent,
    AccessDeniedComponent,
    ViewBookingComponent,
    TicketsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    DataTablesModule,
    MatFormFieldModule,
    FormsModule,
    NgbModule,
    MatStepperModule,
    MatSliderModule,
    MatButtonModule,
    NgxLoadingModule.forRoot({
      animationType: ngxLoadingAnimationTypes.rotatingPlane ,
        backdropBackgroundColour: 'rgba(0,0,0,0.8)', 
        backdropBorderRadius: '4px',
        primaryColour: 'rebeccapurple', 
        secondaryColour: 'orange', 
        tertiaryColour: 'white'
    }),
    ToastrModule.forRoot(),
  ],
  providers: [FlightService,FlightResolverService,AirlinesService,AirportService,UserLoginServiceModule,
  CustomerRegisterServiceModule,BookingdetailsService,AdminGuardService,ConfirmEqualValidatorDirective,
  CreateFlightComponent,UserAuthorizationService,BookingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
