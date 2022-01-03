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
    return this.HttpClient.get<Publication[]>(baseURL +"public", httpOptions);
  }

  getAllPublication(): Observable<Publication[]>
  {
    return this.HttpClient.get<Publication[]>(baseURL , httpOptions);
  }

  createPublication(publication: Publication): Observable<any>
  {
    console.log("create publication " , publication);
    return this.HttpClient.post<Publication>(baseURL, publication);
  }

  updatePublication(id:number, publication:Publication):Observable<any>
  {
    return this.HttpClient.put<Publication>(baseURL + id, publication);
  }

  likePublication(id:number):Observable<any>
  {
    return this.HttpClient.put<Publication>(baseURL + "liked/"+ id, httpOptions);
  }
  
  
}
