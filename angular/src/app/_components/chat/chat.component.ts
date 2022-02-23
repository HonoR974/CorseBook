import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Message } from 'src/app/_class/message';
import { ChatService } from 'src/app/_services/chat.service';
import { MessageService } from 'src/app/_services/message.service';

import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { WebSocketService } from 'src/app/_services/web-socket.service';


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements  OnInit, OnDestroy {

  id:number;

  user:any;

  messages: Message[] = [];

  //recuperer webSocketService.chatMessages
  //pour lui ajouter la discussion entre deux user 
  //la discussion est sauvegardé a chaque envois de message 

  constructor(public webSocketService: WebSocketService,
                private route: ActivatedRoute, 
              private tokenService: TokenStorageService, 
              private chatService:ChatService, 
              private messageService: MessageService) { }

  ngOnInit(): void {
    this.webSocketService.openWebSocket();

    this.user = this.tokenService.getUser().username;
    this.getMessageByIdChat();
    this.addMessageToWebSocket();
    
  }

  ngOnDestroy(): void {
    console.log("list message a la fin    ", this.messages );
    this.webSocketService.closeWebSocket();
    this.returnMessageFromWebSocket();
    this.saveMessage
  }

  sendMessage(sendForm: NgForm) {
    const chatMessageDto = new Message(this.user, sendForm.value.message);
    this.webSocketService.sendMessage(chatMessageDto);
    sendForm.controls.message.reset();

    // API 
    this.messages.push(new Message(this.user, sendForm.value.message));
  }

  getMessageByIdChat()
  {
    this.id = this.route.snapshot.params['id'];
    this.messageService.getMessageByIdChat(this.id).subscribe( data => 
      {
        console.log("messages ", data);
        this.messages = data;
      });
  }

  // API -> WS 
  addMessageToWebSocket()
  {
    this.webSocketService.setMessage(this.messages);
  }
 
  // WS -> API 
  returnMessageFromWebSocket()
  {
    this.messages = this.webSocketService.getMessage();
    console.log("this message " , this.messages);
  }

  saveMessage()
  {
    this.messageService.saveMessagesByIdChat(this.id, this.messages).subscribe ( 
      data => 
      {
        console.log("message save in bdd : " , data);
      }
    );
  }
}