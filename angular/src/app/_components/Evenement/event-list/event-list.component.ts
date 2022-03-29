import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Comment } from 'src/app/_class/comment';

import { Evenement } from 'src/app/_class/evenement';
import { User } from 'src/app/_class/user';

import { CommentService } from 'src/app/_services/comment.service';
import { EventService } from 'src/app/_services/event.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {

  events:Evenement[] = [];

//agm 
  // google maps zoom level
  zoom: number = 8;

  // initial center position for the map
  lat: number = 41.91;
  lng: number =  8.73;

  user!:User;
  isConnected:boolean = false;

  
  commentForm =  new FormGroup({
    contenu: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
    ])
  });


  constructor(private eventService: EventService,
              private tokenService: TokenStorageService,
              private commentService: CommentService) { }

  ngOnInit(): void {
    this.getAllEvent();

    if ( this.tokenService.getToken())
    {
      this.isConnected = true;
      this.user = this.tokenService.getUser();
    }
  }

  getAllEvent()
  {
    this.eventService.getallEvent().subscribe( data => 
      {
        this.events = data;
        console.log("getEvent ", data);
        if (this.isConnected)
        {
          this.checkListEvent();
        }
      });
  }

  //ajoute l'user a l'event selectionné 
  addParticipant(id:number)
  {
    console.log("add", id );
    this.eventService.addUserOnEvent(id).subscribe( data =>
      {
        console.log("data ", data);
      })

  }

  checkListEvent()
  {
    this.events.forEach(element => 
       element.isParticiped =  this.isParticiped(element) );
    console.log("event " ,this.events);
  }

  //verifie que l'username de l'user connecté 
  //est dans la liste des participants 
  isParticiped( evenement: Evenement ):boolean
  {
    
    let condition = false;

    for ( let participant of evenement.listParticipant)
    {
      if (this.user.username === participant)
      {
        condition = true;
      }
    }
    return condition;
  }




  //---------- Comment 

  getComments(id:number)
  {
    
   
    

  }

  
  //like comment 
  commentLiked(id:number)
  {

    
    this.commentService.commentLikedById(id)
        .subscribe ( data => 
          {
            console.log("data liked ", data ); 
          });

   
  }

  createComment(id:number)
  {
    let comment = new Comment();
    comment.contenu =  this.commentForm.controls['contenu'].value;
    comment.username = this.tokenService.getUser().username;


    this.commentService.createCommentByEvent(comment, id)
    .subscribe( data => {
      console.log("comment created ", data ); 
    });

  }
}
