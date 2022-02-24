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

  
            
  //debut             
  ngOnInit(): void 
  {
    this.webSocketService.openWebSocket();

    this.user = this.tokenService.getUser().username;
    this.getMessageByIdChat();
  }

  //fin 
  ngOnDestroy(): void {
    
    // API
    this.returnMessageFromWebSocket(); 
    this.saveMessage();
    this.webSocketService.closeWebSocket(); 
  }



  getMessageByIdChat():void
  {
    this.id = this.route.snapshot.params['id'];
    this.messageService.getMessageByIdChat(this.id).subscribe( data => 
      {
     
        this.messages = data;
        this.webSocketService.chatMessages = this.messages;
        console.log("message recupere " , this.webSocketService.chatMessages);
      });
 }


  sendMessage(sendForm: NgForm) :void {
    const chatMessageDto = new Message(this.user, sendForm.value.message);
    this.webSocketService.sendMessage(chatMessageDto);
    sendForm.controls.message.reset();
    
  
   var newMessage = new Message(this.user, sendForm.value.message);
  }
 
  // WS -> API 
  returnMessageFromWebSocket()
  {
    this.messages = this.webSocketService.getMessage();
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