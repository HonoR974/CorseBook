import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Injectable,Component } from '@angular/core';
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

  //getPublicationByUserID
  getPublicationByUserID(id:number):Observable<Publication[]>
  {
    return this.HttpClient.get<Publication[]>(baseURL +"user/"+id);
  }

  //publications public 
  getPublicationPublic(): Observable<Publication[]>
  {
    return this.HttpClient.get<Publication[]>(baseURL +"public");
  }

  //like 
  likePublication(id:number):Observable<any>
  {
    return this.HttpClient.put<Publication>(baseURL + "liked/"+ id, httpOptions);
  }

  //dislike 
  dislikedPublication(id:number):Observable<any>
  {
    return this.HttpClient.put<Publication>(baseURL + "disliked/"+ id, httpOptions);
  }


  //------------- CRUD -------------//
  getAllPublication(): Observable<Publication[]>
  {
    return this.HttpClient.get<Publication[]>(baseURL , httpOptions);
  }

  getPublicationById(id:number): Observable<any>
  {
    return this.HttpClient.get<Publication>(baseURL + id , httpOptions);
  }

  createPublication(publication: Publication): Observable<any>
  {
    
    return this.HttpClient.post<Publication>(baseURL, publication);
  }

  updatePublication(id:number , publication:Publication):Observable<Object>
  {  
    console.log("publication to API " , publication);

    return this.HttpClient.put<Publication>(baseURL + id, publication, httpOptions);
  }

  deletePublication(id:number):Observable<Object> 
  {
    return this.HttpClient.delete(baseURL + id);
  }




  
  
}
