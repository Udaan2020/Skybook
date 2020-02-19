import { Component, OnInit, ViewChild, Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { ToastrService } from 'ngx-toastr';

import { UserLoginServiceModule } from './login.service';

declare var FB: any;

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
  uId:any;
  message: string;
  errMsg: string;
  showLoadingIndicator = false;
  constructor(private _userservice: UserLoginServiceModule, private _router: Router,
    private _route: ActivatedRoute, private toasterService: ToastrService) { }

  ngOnInit() {

    this.message = "User Logged In Succesful ";
    this.errMsg = " Error in Logging in";
    (window as any).fbAsyncInit = function() {
      FB.init({
        appId      : '225630108471076',
        cookie     : true,
        xfbml      : true,
        version    : 'v6.0'
      });
      FB.AppEvents.logPageView();
    };

    (function(d, s, id){
       var js, fjs = d.getElementsByTagName(s)[0];
       if (d.getElementById(id)) {return;}
       js = d.createElement(s); js.id = id;
       js.src = "https://connect.facebook.net/en_US/sdk.js";
       fjs.parentNode.insertBefore(js, fjs);
     }(document, 'script', 'facebook-jssdk'));

  }

  userLogin(): void {
    this.showLoadingIndicator=true;
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
          this.showLoadingIndicator=false;
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
          this.showLoadingIndicator=false;

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
        this.showLoadingIndicator = false;
      }
    );

  }



  submitLogin(){
    this.showLoadingIndicator = true;
    console.log("submit login to facebook");
    // FB.login();
    FB.login((response)=>
        {
          console.log('submitLogin',response);
          if (response.authResponse)
          {
            this.toasterService.success(
              "Welcome to Skybook.com",
              "Facebook Login Success!",
              {
                timeOut: 8000,
                closeButton: true,
                progressBar: true,
              });
              
              var loguser={
                userName:'',
                userId:'',
                photo:''                
              }
              var cuser;
              FB.api('/me?fields=id,name,first_name,gender,picture.width(150).height(150),age_range,friends',
              function(result) {
                  if (result && !result.error) {
                      cuser = result;
                       loguser.userId = cuser.first_name;
                       loguser.userName = cuser.name;
                       loguser.photo = cuser.picture.data.url;
                       localStorage.setItem('currentUser', JSON.stringify(loguser));
                       UserLoginServiceModule.loggedINUser = JSON.stringify(localStorage.getItem('currentUser'));
                       UserLoginServiceModule.loginEventEmitter.emit(UserLoginServiceModule.loginEventEmitter);
                      console.log(loguser);
                  } else {
                      console.log(result.error);
                  }
              });
            //   var loguser={
            //     userName:this.uId,
            //     userId: this.uId
            // }
              console.log(response);
             
              this._router.navigate(['/']);
              this.showLoadingIndicator = false;
           }
           else
           {
            this.toasterService.error(
              "Error occured in logging with facebook",
              "ERROR!",
              {
                timeOut: 8000,
                closeButton: true,
                progressBar: true,
              });
              this._router.navigate(['/user-login']);
              this.showLoadingIndicator = false;
         }
      });

  }

  

}
