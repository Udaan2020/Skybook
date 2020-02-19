import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Booking } from 'src/app/models/booking.model';

@Injectable({
  providedIn: 'root'
})
export class BookingdetailsService {

  private bookingDetailUrl = "http://localhost:8081/booking/";

  constructor(
    private http:HttpClient
  ) { }

  getBookingById(bookingId:number){
    return this.http.get<Booking>(this.bookingDetailUrl+"get/"+bookingId);
  }

  deleteBookingById(bookingId:number){
    return this.http.delete<boolean>(this.bookingDetailUrl+"remove/"+bookingId);
  }
}
