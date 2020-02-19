import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UserLoginServiceModule } from '../components/user/user-login/login.service';

@Injectable({
  providedIn: 'root'
})
export class UserAuthorizationService {

  loggedUser : string;
  userId : string;
  constructor(private _router: Router)
  {

  }
  canActivate(route : ActivatedRouteSnapshot, state : RouterStateSnapshot) : boolean
  {
      if (localStorage.length > 0) {

          this.loggedUser = JSON.stringify(localStorage.getItem('currentUser'));
          this.userId = JSON.parse(localStorage.getItem('currentUser'))["userId"];
        }
        else {
          UserLoginServiceModule.loginEventEmitter.subscribe((data) => {
            this.loggedUser = UserLoginServiceModule.loggedINUser;
            this.userId = JSON.parse(localStorage.getItem('currentUser'))["userId"];
          });
        }

       if(this.loggedUser)
       {
           return true;
       } 
       else
       {
           this._router.navigate(['/login']);
           return false;
       }

  }
}
