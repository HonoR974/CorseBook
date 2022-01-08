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

  createCommentByPublication(comment:Comment, id_publication:number):Observable<any>
  {
    console.log("create comment " , comment);

    return this.HttpClient.post<Comment>(baseURL + "publication/"+id_publication, comment);
  }
}
