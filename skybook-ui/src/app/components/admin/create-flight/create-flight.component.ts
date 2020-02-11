import { Component, OnInit, ViewChild } from '@angular/core';
import { AirportService } from 'src/app/services/admin/airport.service';
import { AirlinesService } from 'src/app/services/admin/airlines.service';
import { FormGroup, FormControl, Validators, NgForm, NgModel} from '@angular/forms';
import { FlightService } from 'src/app/services/admin/flight.service';
import { Flight } from 'src/app/models/flight.model';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FlightComponent } from '../flight/flight.component';

@Component({
  selector: 'app-create-flight',
  templateUrl: './create-flight.component.html',
  styleUrls: ['./create-flight.component.css']
})
export class CreateFlightComponent implements OnInit {

  public airports ;
  public airlines ;
  public copyflight;
  public validMessage : string = '';
  flight : Flight =
  {
    flightNo : null,
    model: null,
    economySeat: null,
    businessSeat: null,
    cabinLuggage: null,
    checkInLuggage: null,
    sourceAirport : null,
    destinationAirport : null,
    airOperator: null,
  }
  submitted = false;
  models: string[] =[
    'Airbus A220','Airbus A300', 'Airbus A350', 'Boeing 737',
    'Boeing 727', 'Boeing 787', 'Boeing Model 221', 'IAR-821',
    'RotorWay Exec', 'Ruschmeyer R 90'

  ]
  formHeading : string;
  submitText : string;
  non_editable : boolean;
  message : string;
  errMsg : string;
  msgHead : string;
  errHead : string;
  flightId : string;

  @ViewChild('addflightform', null) public addFlightForm : NgForm;
  @ViewChild('addflightid', null)public addflightid : NgModel;

  constructor(private airportService : AirportService,
    private airlineService : AirlinesService,
    private flightService : FlightService,private router : Router,
    private toastr : ToastrService,
    private route : ActivatedRoute)
     { }


  ngOnInit() {

    this.airportService.getAllAirports().subscribe(
      data=> {this.airports = data}
    );

    this.airlineService.getAllAirlines().subscribe(
      data=> {this.airlines = data}
    );
    this.route.params.subscribe(routeParams =>{this.flightId = routeParams.flightid;
    this.getFlight(this.flightId)}
    );
   
  }

  getFlight(id:string)
  {
      if(id === '0')
      {
        this.flight = 
        {
          flightNo : null,
          model: null,
          economySeat: null,
          businessSeat: null,
          cabinLuggage: null,
          checkInLuggage: null,
          sourceAirport : null,
          destinationAirport : null,
          airOperator: null,
        }
        this.addFlightForm.reset();
        this.formHeading = "Flight Registration";
        this.submitText="Add a Flight";
        this.msgHead="FLIGHT ADDED!";
        this.message = "A new flight has been successfully registered";
        this.errHead = "ERROR";
        this.errMsg = " Error in adding the new Flight. Flight already Exists. Please try with some different Flight No.";
        this.non_editable = false;
      }
      else{
        this.flightService.getAllFlights().subscribe(
          (flightList: any) => {
            let _flightList = flightList as Flight[];
            this.flight = _flightList.find(f => f.flightNo === id);
          }
        );
        this.formHeading = "Flight Updation";
        this.submitText="Update Flight";
        this.msgHead="FLIGHT UPDATED!";
        this.message = "Flight with Flight ID : " + id + " has been successfully updated";
        this.errHead = "ERROR";
        this.errMsg = " Error in updating the Flight. Please try after few minutes."
        this.non_editable = true;
      }
  }
  

  gotoList() {
    this.router.navigate(['/admin/flight/all']);
  }

  onSubmit() {
    this.submitted = true;
    this.createFlight();    
  }

  isDisabled(): boolean {
    return this.non_editable;
  }

  goBack()
  {
    this.router.navigate(['admin/flight/all']);
  }
  createFlight()
  {
    let flt = Object.assign({}, this.flight);
    
    if(flt.sourceAirport === flt.destinationAirport)
    { 
      this.toastr.warning(
        "Source and Destination Airport cannot be same. Please enter different airport for Departure and Arrival",
        "Warning!",
      {
        timeOut: 8000,
        closeButton: true,
        progressBar: true,
      });
      this.addFlightForm.reset();
    }
    else
    {
    this.flightService.createFlight(flt,this.flightId).subscribe(
      data => {

        this.toastr.success(
          this.message,
          this.msgHead,
        {
          timeOut: 8000,
          closeButton: true,
          progressBar: true,
        });
        this.addFlightForm.reset();
        this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
        this.router.navigate(['admin/flight/all']));

      },
      err=>
      {
        this.toastr.error(
          this.errMsg,
          this.errHead,
        {
          timeOut: 8000,
          closeButton: true,
          progressBar: true,
        });
        this.addFlightForm.reset();
        this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
        this.router.navigate(['admin/flight/all']));
      }

    )
  }
}

}
