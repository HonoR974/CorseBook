import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/_class/user';


const baseURL = "http://localhost:8080/api/contact/";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(private HttpClient: HttpClient) { }


  getContactList():Observable<User[]> 
  {
    
    return this.HttpClient.get<User[]>(baseURL + "list", httpOptions);
  }
}
