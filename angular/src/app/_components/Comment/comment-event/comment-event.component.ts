import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Evenement } from 'src/app/_class/evenement';
import { CommentService } from 'src/app/_services/comment.service';
import { EventService } from 'src/app/_services/event.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { Comment } from 'src/app/_class/comment';
@Component({
  selector: 'app-comment-event',
  templateUrl: './comment-event.component.html',
  styleUrls: ['./comment-event.component.css']
})
export class CommentEventComponent implements OnInit {

  constructor( private tokenStorage: TokenStorageService,
                private commentService : CommentService,
                private eventService:EventService) { }


  @Input()
  eventRequest!:Evenement;


  imgStandardPath : string = "https://testp12.s3.eu-west-3.amazonaws.com/image/user.png";
  
  event!:Evenement;
  comment:Comment =new Comment;

  commentForm =  new FormGroup({
    contenu: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
    ])
  });


  ngOnInit(): void 
  {
    this.event = this.eventRequest;

  }


   //create comment event
   createComment( id_event: number)
   {
     console.log("create ");
     
     this.comment.contenu = this.commentForm.controls['contenu'].value;
     this.comment.username = this.tokenStorage.getUser().username;
   
 
 
       this.commentService.createCommentByEvent(this.comment, id_event)
                           .subscribe( data =>
                              { 
                               console.log("data comment created ", data);
                               this.commentForm.reset();
                               this.getEventByID();
                             });
   }
 

    //like comment 
    commentLiked(id:number)
    {
      this.commentService.commentLikedById(id)
                        .subscribe ( data => 
                          {
                            console.log("data Liked " ,  data);
                            this.getEventByID();
                          });
  
    }
  
    commentDisliked(id:number)
    {
      this.commentService.commentDislikedById(id).subscribe( data => 
        {
          console.log("data disliked ", data ); 
          this.getEventByID();
        });
    }

    
    getEventByID()
    {
      this.eventService.getEventByID(this.event.id).subscribe(
        data => 
        {
          this.event = data;
          console.log("data event ", data);
        }
      )
    }

}




