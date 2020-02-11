import { Component, OnInit, ViewChild, Input, ViewEncapsulation } from '@angular/core';
import { NgbCarousel, NgbSlideEvent, NgbSlideEventSource, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AirlinesService } from 'src/app/services/admin/airlines.service';
import { AirportService } from 'src/app/services/admin/airport.service';
import { FlightService } from 'src/app/services/admin/flight.service';
import { ScheduleFlightService } from 'src/app/services/user/schedule-flight.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  encapsulation: ViewEncapsulation.None,
  styles: [`
    .dark-modal .modal-content {
      background-color: #292b2c;
      color: white;
    }
    .dark-modal .close {
      color: white;
    }
    .light-blue-backdrop {
      background-color: #5cb3fd;
    }
  `],
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  public airports ;
  public flights;
  public hidden : boolean;
  images = [945, 104, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);

  @Input()
  src:string = '';

  @Input()
  dest:string = '';

  @Input()
  deptDt : string = '';

  closedResult : string;

  constructor(private airlineService : AirlinesService, private airportService : AirportService,
    private scheduleFlightService : ScheduleFlightService,private toastr : ToastrService,
    private modalService : NgbModal,private router : Router)
  {
    this.airportService.getAllAirports().subscribe(
      data=>{this.airports =  data}
    );
  }
  ngOnInit() {
    
    this.flights = [];
    this.hidden = true;
  }

  openScrollableContent(longContent) {
    this.modalService.open(longContent, { scrollable: true });
  }

  bookFlight(id : string)
  {
    this.router.navigate(['user/booking/new', id]);
  }

  searchFlight()
  {
    if(this.src === this.dest )
    {
      this.toastr.warning(
        "Source and Destination Airport should be different",
      "Same Source and Destination!",
      {
        timeOut: 8000,
        closeButton: true,
        progressBar: true,
      });
    }
    else if(this.src === ''||this.dest===''||this.deptDt === '')
    {
      this.toastr.warning(
        "All Field should be entered",
      " Invalid Field!",
      {
        timeOut: 8000,
        closeButton: true,
        progressBar: true,
      });
    }
    else
    {
    this.scheduleFlightService.searchFlight(this.deptDt,this.src,this.dest).subscribe(
      data => {this.flights=data}
    );
    this.hidden = false;
    }
  }
}
