import { Injectable } from '@angular/core';
import { FlightService } from './flight.service';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Resolve } from '@angular/router';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class FlightResolverService  implements Resolve<Object>{

  constructor(private flightService : FlightService) { }

  resolve(route : ActivatedRouteSnapshot, state : RouterStateSnapshot) : Observable<Object>
  {
     return this.flightService.getAllFlights();
  }
}
