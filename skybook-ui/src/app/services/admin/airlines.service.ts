import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AirlinesService {

  constructor(private http : HttpClient) { }

  getAllAirlines()
  {
    return this.http.get('/server/api/airlines/list');
  }

  getairlineByID(id: string)
  {
    return this.http.get('/server/api/airlines/get/'+id);
  }
}
