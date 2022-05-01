import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Comment } from 'src/app/_class/comment';
import { Evenement } from 'src/app/_class/evenement';

import { Publication } from 'src/app/_class/publication';
import { CommentService } from 'src/app/_services/comment.service';
import { PublicationService } from 'src/app/_services/publication.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-comment-pub',
  templateUrl: './comment-pub.component.html',
  styleUrls: ['./comment-pub.component.css']
})
export class CommentPubComponent implements OnInit {

  publication!:Publication;
  comment:Comment =new Comment;

  commentForm =  new FormGroup({
    contenu: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
    ])
  });


  //lien de l'image par default des user 
  urlIconUser:any = "https://testp12.s3.eu-west-3.amazonaws.com/image/user.png";

  constructor(private publicationService : PublicationService,
              private tokenStorage: TokenStorageService,
              private commentService : CommentService) { }

  @Input()
  publicationRequest!: Publication;


  ngOnInit(): void {

    this.publication = this.publicationRequest;
    this.getPublicationById();
  }
  



  //----------------- Comments  ---------------//

  //create comment by publication  
  createComment( id_publication: number)
  {
    console.log("create ");
    
    this.comment.contenu = this.commentForm.controls['contenu'].value;
    this.comment.username = this.tokenStorage.getUser().username;
  


      this.commentService.createCommentByPublication(this.comment, id_publication)
                          .subscribe( data =>
                             { 
                              console.log("data comment created ", data);
                              this.commentForm.reset();
                              this.getPublicationById();
                            });
  }

  //like comment 
  commentLiked(id:number)
  {
    this.commentService.commentLikedById(id)
                      .subscribe ( data => 
                        {
                          console.log("data Liked " ,  data);
                          this.getPublicationById();
                        });

  }

  commentDisliked(id:number)
  {
    this.commentService.commentDislikedById(id).subscribe( data => 
      {
        console.log("data disliked ", data ); 
        this.getPublicationById();
      });
  }

  getPublicationById()
  {
    this.publicationService.getPublicationById(this.publication.id).subscribe( 
      data => {
        this.publication = data;
        console.log("data pub ", data);
      }
    )
  }


}
