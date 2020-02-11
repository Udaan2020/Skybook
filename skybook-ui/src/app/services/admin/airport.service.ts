import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AirportService {

  constructor(private http : HttpClient) { }

  getAllAirports()
  {
      return this.http.get('/server/api/airport/list');
  }

  getairportByID(id: string)
  {
    return this.http.get('/server/api/airport/get/'+id);
  }
}
