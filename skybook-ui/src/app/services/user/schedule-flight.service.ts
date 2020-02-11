import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { FlightService } from '../admin/flight.service';

const httpOptions = {
  headers : new HttpHeaders({'Content-Type' :  'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class ScheduleFlightService {

  public flight;
  constructor(private http:HttpClient, private toastr : ToastrService,private flightService : FlightService) { }

  searchFlight(deptDt:string ,src:string, dest : string)
  {
      return this.http.get('/server/api/schedule-flight/'+deptDt+'/'+src+'/'+dest);
  }

  getAllFlights()
  {
    return this.http.get('/server/api/schedule-flight/list');
  }
  getFlightbyId(id: string)
  {
    return this.http.get('/server/api/schedule-flight/'+id);
  }

  scheduleFlight(departureDate:string,  arrivalDate : string, departureTime:string,
    arrivalTime:string, businessPrice:string, economicPrice:string, flightNO :string
    ):Observable<any>
  {
    this.flightService.getFlightById(flightNO).subscribe(
      data=>{this.flight = data}
    );
    var deptD = departureDate.split("-"); 
    var arrD = arrivalDate.split("-");

    console.log(JSON.stringify(this.flight.flightNo));
     return this.http.post('/server/api/schedule-flight/create',
     {
      "departureDate": deptD[1]+"-"+deptD[2]+"-"+deptD[0],
      "arrivalDate": arrD[1]+"-"+arrD[2]+"-"+arrD[0],
      "departureTime": departureTime,
      "arrivalTime": arrivalTime,
      "economicPrice": economicPrice,
      "businessPrice": businessPrice,
      "flight": {
          "flightNo": this.flight.flightNo,
          "model": this.flight.model,
          "economySeat": this.flight.economySeat,
          "businessSeat": this.flight.businessSeat,
          "cabinLuggage": this.flight.cabinLuggage,
          "checkInLuggage": this.flight.checkInLuggage,
          "sourceAirport": {
              "airportId": this.flight.sourceAirport.airportId,
              "airportName": this.flight.sourceAirport.airportName,
              "city": this.flight.sourceAirport.city,
              "country": this.flight.sourceAirport.country,
          },
          "destinationAirport": {
              "airportId": this.flight.destinationAirport.airportId,
              "airportName": this.flight.destinationAirport.airportName,
              "city": this.flight.destinationAirport.city,
              "country": this.flight.destinationAirport.country
          },
          "airlines": {
              "airlineId": this.flight.airlines.airportId,
              "airlineName": this.flight.airlines.airportName,
          }
      },
      "flightStatus": "On Time",
      "economicBookedSeats": 0,
      "businessBookedSeats": 0
  },
     httpOptions);
  }
}
