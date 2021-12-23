import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Publication } from '../_class/publication';

const baseURL = "http://localhost:8080/api/publication/";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class PublicationService {

  constructor(private HttpClient: HttpClient) { }

  getPublicationPublic(): Observable<Publication[]>
  {
    return this.HttpClient.get<Publication[]>(baseURL +"public" , httpOptions);
  }

  
}
