import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Chat } from 'src/app/_class/chat';
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

  title ! :string;

  chat:Chat = new Chat;
 

  constructor(public webSocketService: WebSocketService,
                private route: ActivatedRoute, 
              private tokenService: TokenStorageService, 
              private chatService:ChatService, 
              private messageService: MessageService) { }

  
            
  //debut             
  ngOnInit(): void 
  {
    this.id = this.route.snapshot.params['id'];

    this.webSocketService.openWebSocket(this.id);

    this.user = this.tokenService.getUser().username;

    this.getChat();
  }

  //fin 
  ngOnDestroy(): void {
    
    // API
    this.returnMessageFromWebSocket(); 
    this.saveMessage();
    this.webSocketService.closeWebSocket(); 
  }





  sendMessage(sendForm: NgForm) :void {
    const chatMessageDto = new Message(this.user, sendForm.value.message);
    this.webSocketService.sendMessage(chatMessageDto);
    sendForm.controls.message.reset();
    
    
   //API 
   this.saveMessage();
  }
 
  // WS -> API 
  returnMessageFromWebSocket()
  {
    this.messages = this.webSocketService.getMessage();
  }


  saveMessage()
  {

    this.messageService.saveMessagesByIdChat(this.id, this.webSocketService.getMessage()).subscribe ( 
      data => 
      {
      }
    );
  }


  getChat()
  {
    this.chatService.getById(this.id).subscribe( data => 
      {
        console.log("chat get ", data); 
        this.chat = data;
      });
  }

}