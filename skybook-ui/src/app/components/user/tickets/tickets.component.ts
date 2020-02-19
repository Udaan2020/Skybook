import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ScheduleFlightService } from 'src/app/services/user/schedule-flight.service';
import * as jspdf from 'jspdf';  
  
import html2canvas from 'html2canvas';  
import { PassengerService } from 'src/app/services/user/passenger.service';

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {

  public flt;
  public pnr;
  public images;
  public n;
  public fare;
  public passengers;
  constructor(private route : ActivatedRoute, private flightService : ScheduleFlightService,
    private passengerService : PassengerService) { 

    this.route.params.subscribe(routeParams => { 
      this.flightService.getFlightbyId(routeParams.flightId.toString()).subscribe(
       data=>{this.flt = data;
       } 
      );
      this.passengerService.getPassenger(routeParams.pnr.toString()).subscribe(
        data=>{this.passengers = data

        }
      );

      this.pnr = routeParams.pnr.toString();
      this.fare = routeParams.fare.toString();
     });
  }

  ngOnInit() {

    this.n = Math.floor(Math.random() * Math.floor(1000)).toString();
    this.images =  "https://picsum.photos/id/"+this.n+"/900/500";
    
  }

  public downloadTicket()  
  {  
    var data = document.getElementById('ticket');  
    html2canvas(data).then(canvas => {  
      var imgWidth = 208;   
      var pageHeight = 550;    
      var imgHeight = canvas.height * imgWidth / canvas.width;  
      var heightLeft = imgHeight; 
      var pageWidth = 208; 
  
      const contentDataURL = canvas.toDataURL('image/png')  
      let pdf = new jspdf('p', 'mm', 'a4'); // A3 size page of PDF  
      var position = 0;  
      pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight);
      pdf.save(this.pnr+".pdf"); // Generated PDF   
    });  
  } 
  
  
  public getTicket()
  {
    var HTML_Width = $(".ticket").width();
    var HTML_Height = $(".ticket").height();
    var top_left_margin = 15;
    var PDF_Width = HTML_Width+(top_left_margin*2);
    var PDF_Height = (PDF_Width*1.5)+(top_left_margin*2);
    var canvas_image_width = HTML_Width;
    var canvas_image_height = HTML_Height;
    
    var totalPDFPages = Math.ceil(HTML_Height/PDF_Height)-1;
    
    
    html2canvas($(".ticket")[0],{allowTaint:true}).then(function(canvas) {
    canvas.getContext('2d');
    
    console.log(canvas.height+"  "+canvas.width);
    
    
    var imgData = canvas.toDataURL("image/jpeg", 1.0);
    var pdf = new jspdf('p', 'pt',  [PDF_Width, PDF_Height]);
        pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin,canvas_image_width,canvas_image_height);
    
    
    for (var i = 1; i <= totalPDFPages; i++) { 
    pdf.addPage("pdf","portrait" );
    pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height*i)+(top_left_margin*4),canvas_image_width,canvas_image_height);
    }
  
  
    pdf.save(this.pnr+".pdf");
  });
  
};
}
