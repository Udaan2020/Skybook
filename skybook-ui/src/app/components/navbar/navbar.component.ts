import { Component, OnInit } from '@angular/core';
import { UserLoginServiceModule } from '../user/user-login/login.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  loggedUser: any = null;
  user_role: any = null;
  username : string ;
  userId : string;
  photo : string;
  message : string;
  errMsg : string = "Logging Out Error";
  showLoadingIndicator = false;
  constructor(private _userService : UserLoginServiceModule,private toasterService: ToastrService,
    private router: Router) { }

  ngOnInit() {
    
    if (localStorage.length > 0) {

      this.loggedUser = JSON.stringify(localStorage.getItem('currentUser'));
      this.user_role = +JSON.parse(localStorage.getItem('currentUser'))["userCategory"];
      this.username = JSON.parse(localStorage.getItem('currentUser'))["userName"];
      this.userId = JSON.parse(localStorage.getItem('currentUser'))["userId"];
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

  logout(){
    this.showLoadingIndicator = true;
    localStorage.clear();
    this._userService.logout(this.userId).subscribe(
      data => {
        if (JSON.stringify(data).indexOf("Success") >= 0) {

          this.toasterService.success(
            "Logout Successfull ",
            this.message,
            {
              timeOut: 3000,
              closeButton: true,
              progressBar: true,
            })
          localStorage.setItem('currentUser', JSON.stringify(data));
          UserLoginServiceModule.loggedINUser = JSON.stringify(localStorage.getItem('currentUser'));
          UserLoginServiceModule.loginEventEmitter.emit(UserLoginServiceModule.loginEventEmitter); 
          this.router.navigate(['/']);
          this.showLoadingIndicator = false;
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
          this.router.navigate(['']);
          this.showLoadingIndicator = false;

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
        
        this.router.navigate(['']);
        this.showLoadingIndicator = false;
      }
    );

  
    UserLoginServiceModule.loggedINUser = null;
    UserLoginServiceModule.loginEventEmitter.emit(UserLoginServiceModule.loggedINUser);
    this.router.navigate(['/login']);
    this
  }

}
