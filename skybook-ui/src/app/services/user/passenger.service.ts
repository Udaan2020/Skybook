import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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
export class PassengerService {

  constructor(private http:HttpClient) { }

  addPassengers(passengers:any)
  {
    console.log(JSON.stringify(passengers));
    return this.http.post('http://localhost:8083/api/booking/passenger/all/add',
    JSON.stringify(passengers),
    httpOptions);
  }

  getPassenger(pnr:string):Observable<any>
  {
     return this.http.get('http://localhost:8083/api/booking/passenger/get/'+pnr)
  }
  
}
