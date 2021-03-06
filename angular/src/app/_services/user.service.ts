import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FileAPI } from '../_class/file-api';

const API_URL = 'http://localhost:8080/api/user/';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  
  constructor(private http: HttpClient) { }

  getAllUser():Observable<any>
  {
    return this.http.get(API_URL + "all", httpOptions)
  }

  getAllUsername():Observable<any>
  {
    return this.http.get(API_URL + "all/username", httpOptions)
  }

  getUserById(id: number):Observable<any>
  {
    return this.http.get(API_URL + id , httpOptions);
  }

  getUserByUsername(username:string):Observable<any>
  {
    return this.http.get(API_URL +"jwt/" +username, httpOptions);
  }

  updateProfilePicture(file:FileAPI):Observable<Object>
  {
    return this.http.put(API_URL + "file" , file );
  }
}