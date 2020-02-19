import { Component, OnInit } from '@angular/core';
import { BookingService } from 'src/app/services/user/booking.service';
import { UserLoginServiceModule } from '../user-login/login.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-booking',
  templateUrl: './view-booking.component.html',
  styleUrls: ['./view-booking.component.css']
})
export class ViewBookingComponent implements OnInit {

  loggedUser: any = null;
    user_role: any = null;
    username : string ;
    userId : string;
    public bookings;
  constructor(private bookingService:BookingService,
    private toastr:ToastrService, private router:Router) { }

  ngOnInit() {

    if (localStorage.length > 0) {

      this.loggedUser = JSON.stringify(localStorage.getItem('currentUser'));
      this.user_role = +JSON.parse(localStorage.getItem('currentUser'))["userCategory"];
      this.username = JSON.parse(localStorage.getItem('currentUser'))["userName"];
      this.userId = JSON.parse(localStorage.getItem('currentUser'))["userId"];
      console.log(JSON.parse(localStorage.getItem('currentUser')));
    }
    else {
      UserLoginServiceModule.loginEventEmitter.subscribe((data) => {
        this.loggedUser = UserLoginServiceModule.loggedINUser;
        this.user_role = +JSON.parse(localStorage.getItem('currentUser'))["userCategory"];
        this.username = JSON.parse(localStorage.getItem('currentUser'))["userName"];
        this.userId = JSON.parse(localStorage.getItem('currentUser'))["userId"];
        console.log(JSON.parse(localStorage.getItem('currentUser')));
      });
    }

    this.bookingService.getBookbyUserId(this.userId).subscribe(
      data=>{this.bookings=data}
    );

  }
  viewBooking(id : string, pnr:String,fare:string)
  {
    this.router.navigate(['user/booking/ticket', id,pnr,fare]);
  }
  cancel(id:string)
  {
    var res=confirm("Are you sure, you want to cancel this booking ?");
    if(res === true)
    {
    this.bookingService.deleteBooking(id).subscribe(
      data=>{
        this.toastr.success(
          "Your Booking Id with id :  "+id+"has been cancelled" ,
          "Booking Cancelled!",
          {
            timeOut: 8000,
            closeButton: true,
            progressBar: true,
          });
          this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
          this.router.navigate(['/user/booking/view']));

      },
      err=>{

        this.toastr.error(
          "Either Booking has already been cancelled or server error! Please try after sometime" ,
          "Warning!",
          {
            timeOut: 8000,
            closeButton: true,
            progressBar: true,
          });
      }
    )
  }
}

}
