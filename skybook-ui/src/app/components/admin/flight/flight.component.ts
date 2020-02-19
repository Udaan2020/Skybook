import { Component, OnInit, Input } from '@angular/core';
import { FlightService } from 'src/app/services/admin/flight.service';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { AirportService } from 'src/app/services/admin/airport.service';
import { NgModule }      from '@angular/core';
import {NgModel} from '@angular/forms';
import {Airport} from '../../../models/airport.model';
import { ToastrService } from 'ngx-toastr';
import { not } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.css']
})
export class FlightComponent implements OnInit {
  public flights;
  public airports ;
  query: Airport;

  @Input()
  public src : string;

  @Input()
  public dest : string;



  constructor(private flightService : FlightService, private airportService : AirportService,
    private router: Router, private route : ActivatedRoute,private toastr : ToastrService) { 
      this.airportService.getAllAirports().subscribe(
        data=>{this.airports =  data}
      );
    }

  ngOnInit() {
    
    this.flights= this.route.snapshot.data['flightList'];
    
  }

  getAllFlights()
  {
     this.flights.subscribe(
       data => {this.flights = data},
       err => console.error(err),
       () => console.log('flights loaded')
     );
  }

  editFlight(flightId : string)
  {
    this.router.navigate(['admin/flight/create-update', flightId]);
  }

  searchFlight()
  {
    this.flightService.searchFlight(this.src,this.dest).subscribe(
      data => {this.flights=data}
    );
    
  }

  scheduleFlight()
  {
    this.router.navigateByUrl('admin/flight/schedule-flight');
  }

  addFlight()
  {
    this.router.navigateByUrl('admin/flight/create-update/0');
  }
  
  delete(flightId : string)
  {
      this.flightService.deleteFlight(flightId).subscribe(
        data => {
          this.toastr.success(
            "Flight with flight id : "  +flightId + "has been successfuly removed !",
          "FLIGHT DELETED!",
          {
            timeOut: 8000,
            closeButton: true,
            progressBar: true,
          });
          this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
          this.router.navigate(['admin/flight/all']));
        },
        error =>
        {
          this.toastr.error("Error in Deleting Flight with Flight ID : " + flightId + " Please check the flight exist or not. Else try after few minutes." ,
          "DELETION FAILED!",
          {
            timeOut: 8000,
            closeButton: true,
            progressBar: true,
          });
        }
      )
  }

}
