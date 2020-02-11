import { Component, OnInit, ViewChild, Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { ToastrService } from 'ngx-toastr';

import { UserLoginServiceModule } from './login.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  @ViewChild('customerLoginform', null) public userLogForm: NgForm;

  existUser: User =
    {
      userId: null,
      userName: null,
      userMail: null,
      userPassword: null,
      userNumber: null,
      userActiveStatus: null,
    }

  message: string;
  errMsg: string;
  constructor(private _userservice: UserLoginServiceModule, private _router: Router,
    private _route: ActivatedRoute, private toasterService: ToastrService) { }

  ngOnInit() {

    this.message = "User Logged In Succesful ";
    this.errMsg = " Error in Logging in";

  }

  userLogin(): void {
    let loguser: User = Object.assign({}, this.existUser);
    this._userservice.userLogin(loguser).subscribe(
      data => {
        if ((JSON.stringify(data).indexOf("Error") >= 0)) {

          this.toasterService.error(
            JSON.stringify(data),
            this.errMsg,
            {
              timeOut: 8000,
              closeButton: true,
              progressBar: true,
            })
          this.userLogForm.reset();
          this._router.navigate(['/user-login']);
        }
        else {



          this.toasterService.success(
            "Login Successfull ! Welcome to Skybook.com",
            this.message,
            {
              timeOut: 3000,
              closeButton: true,
              progressBar: true,
            })
          localStorage.setItem('currentUser', JSON.stringify(data));
          UserLoginServiceModule.loggedINUser = JSON.stringify(localStorage.getItem('currentUser'));
          UserLoginServiceModule.loginEventEmitter.emit(UserLoginServiceModule.loginEventEmitter);
          this.userLogForm.reset();
          this._router.navigate(['/']);

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
        this.userLogForm.reset();
        this._router.navigate(['/user-login']);
      }
    );

  }

}
