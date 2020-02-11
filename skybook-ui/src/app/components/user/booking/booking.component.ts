import {Component, OnInit, Input} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ScheduleFlightService } from 'src/app/services/user/schedule-flight.service';
import { ToastrService } from 'ngx-toastr';
import { BookingService } from 'src/app/services/user/booking.service';
import { UserLoginServiceModule } from '../user-login/login.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  public flightId : string;
  public flight;
  public totalAmount : number;
  
  @Input()
  class : string;

  constructor(private _formBuilder: FormBuilder,private route : ActivatedRoute,
    private flightService : ScheduleFlightService, private toastr : ToastrService,
    private _router: Router, private bookingService:BookingService) 
    {
     this.route.params.subscribe(routeParams => { 
       console.log(routeParams.flightId);
       this.flightService.getFlightbyId(routeParams.flightId.toString()).subscribe(
        data=>{this.flight = data;
        console.log(data)}
        
       )
      });
    }

    loggedUser: any = null;
    user_role: any = null;
    username : string ;
    userId : string;

    @Input()
    payment=
    {
        card:'',
        cvv:''
    };

    public pasengers: any[] = [{
      name: '',
      email: '',
      aadhar: '',
      phone:'',
    }];

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
    
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
    
  }

  book()
  {
    this.toastr.success(
      "Your Booking Id is :  IND6128" ,
      "Booking Confirmed!",
      {
        timeOut: 8000,
        closeButton: true,
        progressBar: true,
      });

        this._router.navigate(['/']);
  }

  bookFlight()
  {
    var pnr = this.flight.flight.flightNo +"-"+this.userId+"-"+Date.now().toString();
    this.bookingService.bookFlight(pnr,this.flight.id,this.userId,"Trans"+pnr,this.totalAmount.toString()
    ).subscribe(
      data=>{
        this.toastr.success(
          "Your PNR is : "+pnr ,
          "Booking Confirmed!",
          {
            timeOut: 8000,
            closeButton: true,
            progressBar: true,
          });
    
            this._router.navigate(['/']);
      },
      err=>{
        this.toastr.error(
          "Error in Booking Please try after some time!",
          "Booking Error",
          {
            timeOut: 8000,
            closeButton: true,
            progressBar: true,
          });
      }

    )
  }
  

  addPassenger() {
    this.pasengers.push({
      name: '',
      email: '',
      aadhar: '',
      phone:'',
    });
  }

  isdisableeconomy()
  {
    if(this.flight.flight.economySeat - this.flight.economicBookedSeats > 0)
    {
      return false;
    }
    else
    {
      return true;
    }
  }

  isdisablebusiness()
  {
    if(this.flight.flight.businessSeat - this.flight.businessBookedSeats > 0)
    {
      return false;
    }
    else
    {
      return true;
    }
  }

  calcAmt()
  {
    if(this.class === 'economy')
    {
      this.totalAmount = this.pasengers.length*Number(this.flight.economicPrice);
    }
    else
    {
      this.totalAmount = this.pasengers.length * Number(this.flight.businessPrice);
    }
  }
  removePassenger(i: number) {
    this.pasengers.splice(i, 1);
  }

  logValue() {
    console.log(this.pasengers);
  }
}
