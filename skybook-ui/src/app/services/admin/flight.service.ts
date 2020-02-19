import { Injectable } from '@angular/core';
import{ HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { Flight } from 'src/app/models/flight.model';
import { Airport } from 'src/app/models/airport.model';
import { Airlines } from 'src/app/models/airlines.model';
import { AirportService } from './airport.service';
import { AirlinesService } from './airlines.service';

const httpOptions = {
  headers : new HttpHeaders({'Content-Type' :  'application/json'})
}


@Injectable({
  providedIn: 'root'
})
export class FlightService {

  public src ;
  public dest;
  public airop;
  constructor(private http:HttpClient, private toastr : ToastrService,
    private airportService : AirportService, private airlineService : AirlinesService) { }

  getAllFlights()
  {

      return this.http.get('/server/api/flight/list');
  }


  searchFlight(src:string, dest : string)
  {
      return this.http.get('/server/api/flight/'+src+'/'+dest);
  }
  
  getFlightById(id : string)
  {
    return this.http.get('server/api/flight/get//' + id);
  }

  createFlight(flight : Flight, fid : string):Observable<any>
  {
      if(fid === "0")
      {
          var res = true;
          if(res === true)
          {
            this.airportService.getairportByID(flight.sourceAirport.toString()).subscribe(
              data => {this.src = data;
              console.log(this.src)},

            );
        
            this.airportService.getairportByID(flight.destinationAirport.toString()).subscribe
            (
              data => {this.dest = data;
                console.log(this.dest)}
            );
        
            this.airlineService.getairlineByID(flight.airOperator.toString()).subscribe(
              data => {this.airop = data;
                console.log(this.airop)}
            );
            return this.http.post('/server/api/flight/create',
            {
              "flightNo": flight.flightNo,
              "model": flight.model,
              "economySeat": flight.economySeat.toString(),
              "businessSeat": flight.businessSeat.toString(),
              "cabinLuggage": flight.cabinLuggage.toString(),
              "checkInLuggage": flight.checkInLuggage.toString,
              "sourceAirport": {
                  "airportId": this.src.airportId,
                  "airportName": this.src.airportName,
                  "city": this.src.city,
                  "country": this.src.country
              },
              "destinationAirport": {
                  "airportId": this.dest.airportId,
                  "airportName": this.dest.airportName,
                  "city": this.dest.city,
                  "country": this.dest.country
              },
              "airlines": {
                  "airlineId": this.airop.airlineId,
                  "airlineName": this.airop.airlineName,
              }
          },
             httpOptions);
          }
      }
      else
      {
        var res = true;
        if(res === true)
        {
         
          return this.http.put('/server/api/flight/update',
            {
              "flightNo": flight.flightNo,
              "model": flight.model,
              "economySeat": flight.economySeat.toString(),
              "businessSeat": flight.businessSeat.toString(),
              "cabinLuggage": flight.cabinLuggage.toString(),
              "checkInLuggage": flight.checkInLuggage.toString,
              "sourceAirport": {
                "airportId": this.src.airportId,
                "airportName": this.src.airportName,
                "city": this.src.city,
                "country": this.src.country
            },
            "destinationAirport": {
                "airportId": this.dest.airportId,
                "airportName": this.dest.airportName,
                "city": this.dest.city,
                "country": this.dest.country
            },
            "airlines": {
                "airlineId": this.airop.airlineId,
                "airlineName": this.airop.airlineName,
            }
          },
             httpOptions);
        }

      }

     
  }

  deleteFlight(id : string)
  {  
    var res = confirm("Are you sure you want to delte the flight with fligh id : "+  id+ " ? All scheduled flight coressponding to this id will also be deleted permanently!");
    if(res === true)
    {
      this.toastr.warning( 
      "All details of the flight with flight id : "  +id + " will be permanently removed ! Corresponding scheduled flight will also be deleted permanently",
      "Warning!",
      {
        timeOut: 8000,
        closeButton: true,
        progressBar: true,
      });
      return this.http.delete('/server/api/flight/remove/'+id, {responseType : 'text'});
    }
  }
  
  
}
