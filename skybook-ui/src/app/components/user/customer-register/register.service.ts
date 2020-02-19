import { Injectable, SystemJsNgModuleLoader, EventEmitter } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

import { User } from 'src/app/models/user.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerRegisterServiceModule {

  constructor(private httpClient: HttpClient, private toastr: ToastrService) { }

  userLogin(newUser: User) : Observable<any> {
    console.log(JSON.stringify(newUser));

    const httpOptions: any = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Access-Control-Allow-Headers': 'Content-Type',
            'Access-Control-Allow-Methods': 'POST',
            'Access-Control-Allow-Origin': '*'
        })
    };
    return this.httpClient.post("http://localhost:9091/user/registration", {
            "userId": newUser.userId,
            "userName": newUser.userName,
            "userMail": newUser.userMail,
            "userPassword": newUser.userPassword,
            "userNumber": newUser.userNumber.toString(),
           // "userCategory": newUser.userCategory.toString(),
            
        })
    }
}
