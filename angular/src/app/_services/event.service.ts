import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Evenement } from '../_class/evenement';


const baseURL = "http://localhost:8080/api/event/";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private HttpClient: HttpClient) { }

  getAllEventByUser():Observable<any>
  {
    return this.HttpClient.get(baseURL + "user", httpOptions);
  }

  //user on event 

  addUserOnEvent(id:number):Observable<Object>
  {
    return this.HttpClient.post(baseURL + "add/user/"+ id , httpOptions);
  }

  deleteUserOnEvent(id:number):Observable<Object>
  {
    return this.HttpClient.post(baseURL + "delete/user/"+ id , httpOptions);
  }

  //CRUD 
  createEvent(event:Evenement ):Observable<Object>
  {
    return this.HttpClient.post(baseURL, event);
  }
  getallEvent():Observable<Evenement[]>
  {
    return this.HttpClient.get<Evenement[]>(baseURL, httpOptions);
  }

  getEventByID(id:number):Observable<any>
  {
    return this.HttpClient.get(baseURL + id, httpOptions);
  }

  deleteEventByID(id:number):Observable<any>
  {
    return this.HttpClient.delete(baseURL + id, httpOptions);
  }

}
