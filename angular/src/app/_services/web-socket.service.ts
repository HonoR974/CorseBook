import { Injectable } from '@angular/core';
import { Message } from '../_class/message';


@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  webSocket: WebSocket;
  chatMessages: Message[] = [];

  constructor() { }

  public openWebSocket(){
    this.webSocket = new WebSocket('ws://localhost:8080/chat');

    this.webSocket.onopen = (event) => {
      console.log('Open: ', event);
    };

    this.webSocket.onmessage = (event) => {
      const chatMessageDto = JSON.parse(event.data);
      this.chatMessages.push(chatMessageDto);
    };

    this.webSocket.onclose = (event) => {
      console.log('Close: ', event);
    };
  }

  public sendMessage(message: Message){
    this.webSocket.send(JSON.stringify(message));
  }

  public closeWebSocket() {
    this.webSocket.close();
  }

  public setMessage(messages:Message[])
  {
    Array.prototype.push.apply(this.chatMessages, messages);
  }

  public getMessage():Message[]
  {
    return this.chatMessages;
  }

}
