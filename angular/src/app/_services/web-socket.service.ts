import { Injectable } from '@angular/core';
import { Message } from '../_class/message';
import { MessageService } from 'src/app/_services/message.service';


@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  webSocket: WebSocket;
  chatMessages: Message[] = [];

  constructor(private messageService:MessageService) { }

  public openWebSocket(id:number){
    this.webSocket = new WebSocket('ws://localhost:8080/chat');

    this.webSocket.onopen = (event) => {
      console.log('Open: ', event);

     this.messageService.getMessageByIdChat(id).subscribe( data => 
      {
        this.chatMessages = data;
        console.log('Open: data  ', data );
      });
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
  
  

  public getMessage():Message[]
  {
    return this.chatMessages;
  }

}
