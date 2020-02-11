import { Component, OnInit } from '@angular/core';
import { Booking } from 'src/app/models/booking.model';
import { BookingdetailsService } from 'src/app/services/user/bookingdetails.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-bookingdetails',
  templateUrl: './bookingdetails.component.html',
  styleUrls: ['./bookingdetails.component.css']
})
export class BookingdetailsComponent implements OnInit {

  bookingId:number;
  bookingDetails:Booking;
  res: boolean;
  pressedButton:boolean;

  constructor(private service: BookingdetailsService, private toastr : ToastrService) { }

  ngOnInit() {
  }

  onSubmit(){
    console.log(
      this.service.getBookingById(this.bookingId)
        .subscribe(
          response=>{
            console.log(response);
            this.bookingDetails = response;
          }
        )
      );
  
  }

  deleteBooking(){
    this.service.deleteBookingById(Number(this.bookingDetails.bookingId))
    .subscribe(
     data=>{
      this.toastr.success(
        "Your Booking with id"+this.bookingId+"has been cancelled",
      " Booking Cancelled!",
      {
        timeOut: 8000,
        closeButton: true,
        progressBar: true,
      });
     },
     error=>
     {
      this.toastr.error(
        "Error in Cancelling Booking.Booking has already been cancelled",
      "Error in Cancelling Booking !",
      {
        timeOut: 8000,
        closeButton: true,
        progressBar: true,
      });

     }
    )

    if(this.res == true)
        alert("Your Booking cancelled Successfully");
        
  }

}
