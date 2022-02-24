import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Chat } from '../_class/chat';
import { Message } from '../_class/message';


const baseURL = "http://localhost:8080/api/message/";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private HttpClient: HttpClient) { }

  getMessageByIdChat(id:number):Observable<Message[]>
  {
    return this.HttpClient.get<Message[]>(baseURL + "chat/"+ id, httpOptions);
  }

  saveMessagesByIdChat(id:number, messages: Message[]):Observable<any>
  {
    console.log("message ", messages);
    return this.HttpClient.post<Message[]>(baseURL + "chat/"+ id, messages,  httpOptions);
  }

}
