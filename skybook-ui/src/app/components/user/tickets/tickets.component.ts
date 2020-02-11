import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ScheduleFlightService } from 'src/app/services/user/schedule-flight.service';
import * as jspdf from 'jspdf';  
import html2canvas from 'html2canvas';  

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {

  public flt;
  public pnr;
  constructor(private route : ActivatedRoute, private flightService : ScheduleFlightService) { 

    this.route.params.subscribe(routeParams => { 
      this.flightService.getFlightbyId(routeParams.flightId.toString()).subscribe(
       data=>{this.flt = data;
       } 
      );
      this.pnr = routeParams.pnr.toString();
     });
  }

  ngOnInit() {

    
  }

  
 
  
}
