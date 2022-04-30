import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Chat } from '../_class/chat';
import { Message } from '../_class/message';


const baseURL = "http://localhost:8080/api/chat/";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(private HttpClient: HttpClient) { }

  getById(id:number):Observable<any>
  {
    return this.HttpClient.get<Chat>(baseURL + id, httpOptions);
  }

  getTitleChatByIdChat(id:number):Observable<any>
  {
    return this.HttpClient.get<any>(baseURL + "title/" + id , httpOptions,  );
  }

}
