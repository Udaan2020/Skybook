import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Booking } from 'src/app/models/booking.model';
import { Observable } from 'rxjs';


const httpOptions = {
  headers : new HttpHeaders({'Content-Type' :  'application/json',
  'Access-Control-Allow-Headers': 'Content-Type',
  'Access-Control-Allow-Methods': 'POST',
  'Access-Control-Allow-Origin': '*'})
}

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(private http:HttpClient) { }

  bookFlight(flightPnr:string, flightId:string,userId:string,transactionId:string,fare:string)
  {
    return this.http.post('http://localhost:8082/api/booking/book',
    {
      
      "flightPnr": flightPnr,
      "flightId": flightId,
      "userId": userId,
      "transactionId": transactionId,
      "fare": fare
    },
    httpOptions);
  }

  getBookbyUserId(uid:string):Observable<any>
  {
    return this.http.get('http://localhost:8082/api/booking/get/user/'+uid,httpOptions);
  }

  deleteBooking(id:string)
  {
    return this.http.delete('http://localhost:8082/api/booking/remove/'+id,httpOptions);
  }
}

