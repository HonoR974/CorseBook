import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from '../_class/comment';


const baseURL = "http://localhost:8080/api/comment/";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private HttpClient: HttpClient) { }

  //create pub comment 
  createCommentByPublication(comment:Comment, id_publication:number):Observable<any>
  {
  

    return this.HttpClient.post<Comment>(baseURL + "publication/"+id_publication, comment);
  }

  //create event comment 
  createCommentByEvent(comment:Comment, id_event:number):Observable<any>
  {


    return this.HttpClient.post<Comment>(baseURL + "event/"+id_event, comment);
  }

  commentLikedById(id:number):Observable<any> 
  {
    return this.HttpClient.post<Comment>(baseURL +"like/" + id, httpOptions);
  }


  commentDislikedById(id:number):Observable<any> 
  {
    return this.HttpClient.post<Comment>(baseURL +"dislike/" + id, httpOptions);
  }


  
}
