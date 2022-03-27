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

  addUserOnEvent(id:number):Observable<Object>
  {
    return this.HttpClient.post(baseURL + "add/"+ id , httpOptions);
  }

  createEvent(event:Evenement ):Observable<Object>
  {
    return this.HttpClient.post(baseURL, event);
  }
  getallEvent():Observable<Evenement[]>
  {
    return this.HttpClient.get<Evenement[]>(baseURL, httpOptions);
  }

}
