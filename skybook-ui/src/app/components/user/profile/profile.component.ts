import { Component, OnInit } from '@angular/core';
import { UserLoginServiceModule } from '../user-login/login.service';
import { HelpdeskService } from 'src/app/services/user/helpdesk.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  loggedUser;
  user_role;
  username;
  userId;
  message;
  photo;
  email;
  showLoadingIndicator = false;
  hidden = true;
  public tickets;
  public allTickets;
  public issue;
  public comment;
  issues = ['Flight Related Issue','Rescheduling a Flight', 'Payment Issue', 'Travel Complain'];
  err=true;
  info=true;
  cancelerr=true;
  cancelinfo=true;
  resolveerr=true;
  resolveinfo=true;
  constructor(private helpdeskService : HelpdeskService) { 

  }

  ngOnInit() {
    if (localStorage.length > 0) {

      this.loggedUser = JSON.stringify(localStorage.getItem('currentUser'));
      this.user_role = +JSON.parse(localStorage.getItem('currentUser'))["userCategory"];
      this.username = JSON.parse(localStorage.getItem('currentUser'))["userName"];
      this.userId = JSON.parse(localStorage.getItem('currentUser'))["userId"];
      this.email = JSON.parse(localStorage.getItem('currentUser'))["userMail"];
      this.message = JSON.parse(localStorage.getItem('currentUser'))["userName"] + "succesfully logged out";
      if(JSON.parse(localStorage.getItem('currentUser'))["photo"] == undefined)
      {
        this.photo = "assets/images/default-profile-picture1.jpg";
      }
      else
      {
        this.photo = JSON.parse(localStorage.getItem('currentUser'))["photo"];
      }
      console.log(JSON.parse(localStorage.getItem('currentUser')));
    }
    else {
      UserLoginServiceModule.loginEventEmitter.subscribe((data) => {
        this.loggedUser = UserLoginServiceModule.loggedINUser;
        this.user_role = +JSON.parse(localStorage.getItem('currentUser'))["userCategory"];
        this.username = JSON.parse(localStorage.getItem('currentUser'))["userName"];
        this.userId = JSON.parse(localStorage.getItem('currentUser'))["userId"];
        this.email = JSON.parse(localStorage.getItem('currentUser'))["userMail"];
        this.message = JSON.parse(localStorage.getItem('currentUser'))["userName"] + "succesfully logged out";
        if(JSON.parse(localStorage.getItem('currentUser'))["photo"] == undefined)
      {
        this.photo = "assets/images/default-profile-picture1.jpg";
      }
      else
      {
        this.photo = JSON.parse(localStorage.getItem('currentUser'))["photo"];
      }
        console.log(JSON.parse(localStorage.getItem('currentUser')));
      });
    }
  }

  raiseTicket()
  {
    this.showLoadingIndicator = true;
    this.helpdeskService.raiseTicket(this.issue,this.comment,this.userId).subscribe
    (
      data =>{
        this.info = false;
        this.showLoadingIndicator=false;
      },
      errr =>
      {
        this.err = false;
        this.showLoadingIndicator=false;
      }
    )
    
  }

  viewTicket()
  {
    this.showLoadingIndicator = true;
    this.helpdeskService.getTicketsByUser(this.userId).subscribe(
      data=>
      {
        this.tickets = data;
        this.hidden=false;
        this.showLoadingIndicator = false;
        window.scrollBy({ 
          top: 600,
          left: 0, 
        });
      }
    )

  }

  viewallTickets()
  {
    this.showLoadingIndicator = true;
    this.helpdeskService.getAllTickets().subscribe(
      data=>
      {
        this.allTickets = data;
        this.hidden=false;
        this.showLoadingIndicator = false;
      }
    )
  }

  resolve(reqId : string)
  {
    var res = confirm("Are you sure this issue has been resolved ?");
    if(res === true)
    {
    this.showLoadingIndicator = true;
    this.helpdeskService.resolve(reqId).subscribe(
      data=>{
        this.resolveinfo = false;
        this.showLoadingIndicator = false;
      },
      err=>
      {
        this.resolveerr = false;
        this.showLoadingIndicator = false;
      }
    )
    }
  }

  cancel(reqId : string)
  {
    var res = confirm("Are you sure yow want to cancel this issue ?");
    if(res === true)
    {
    this.showLoadingIndicator = true;
    this.helpdeskService.cancel(reqId).subscribe(
      data=>{
        this.cancelinfo = false;
        this.showLoadingIndicator = false;
      },
      err=>
      {
        this.cancelerr = false;
        this.showLoadingIndicator = false;
      }
    )
    }
  }

  openNav() {
    document.getElementById("mySidenav").style.width = "100%";
  }
  
  closeNav() {
    document.getElementById("mySidenav").style.width = "0";
  }

}
