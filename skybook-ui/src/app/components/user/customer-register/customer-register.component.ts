import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CustomerRegisterServiceModule } from './register.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-customer-register',
  templateUrl: './customer-register.component.html',
  styleUrls: ['./customer-register.component.css']
})
export class CustomerRegisterComponent implements OnInit {

  @ViewChild('customerRegistrationform', null) public  userRegForm : NgForm;
  newUser: User =
  {
      userId : null,
      userName : null,
      userMail : null,
      userPassword : null,
      userNumber : null,
      userActiveStatus : null,
  }
  message : string;
    errMsg : string;
    constructor(private _registerservice: CustomerRegisterServiceModule, private _router: Router,
        private _route: ActivatedRoute, private toasterService : ToastrService) { }

  ngOnInit() {
    this.message = "Customer Registration Successful";
    this.errMsg = " Error in Registering";
  }
  registeringCustomer():void{
    let regUser : User = Object.assign({},this.newUser);
    this._registerservice.userLogin(regUser).subscribe(
      data => {
        if (JSON.stringify(data).indexOf("Success") >= 0) {

          this.toasterService.success(
            JSON.stringify(data),
            this.message,
            {
              timeOut: 8000,
              closeButton: true,
              progressBar: true,
            })
          this.userRegForm.reset();
          this._router.navigate(['login']);
        }
        else {

          this.toasterService.error(
            JSON.stringify(data),
            this.errMsg,
            {
              timeOut: 8000,
              closeButton: true,
              progressBar: true,
            })
          this.userRegForm.reset();
          this._router.navigate(['login']);

        }
      },
      error => {
        console.log("Error :" + JSON.stringify(error));
        this.toasterService.warning(
          JSON.stringify(error),
          this.errMsg,
          {
            timeOut: 8000,
            closeButton: true,
            progressBar: true,
          })
        this.userRegForm.reset();
        this._router.navigate(['register']);
      }
    );
   
  }
}
