import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

const httpOptions = {
  headers : new HttpHeaders({'Content-Type' :  'application/json',
  'Access-Control-Allow-Headers': 'Content-Type',
  'Access-Control-Allow-Methods': 'POST',
  'Access-Control-Allow-Origin': '*'})
}
@Injectable({
  providedIn: 'root'
})
export class HelpdeskService {

  constructor(private http : HttpClient) { }

  raiseTicket(issue:string, comment:string,userId:string)
  {
    var utc = new Date().toJSON().slice(0,10).replace(/-/g,'/');
    return this.http.post('http://localhost:8084/api/helpdesk/post/issue',
    {
      "userId": userId,
      "issue": issue,
      "comment": comment,
      "date" : utc,
      "status": "Pending",
    },
    httpOptions);
  }

  getTicketsByUser(userId : string)
  {
    return this.http.get('http://localhost:8084/api/helpdesk/get/user/'+userId)
  }

  getAllTickets()
  {
    return this.http.get('http://localhost:8084/api/helpdesk/get/all');
  }

  resolve(reqId : string)
  {
      return this.http.put('http://localhost:8084/api/helpdesk/issue/resolve/'+reqId, httpOptions);
  }

  cancel(reqId : string)
  {
     return this.http.delete('http://localhost:8084/api/helpdesk/issue/remove/'+reqId)
  }
}
