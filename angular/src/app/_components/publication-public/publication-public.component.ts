import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { Comment } from 'src/app/_class/comment';
import { Publication } from 'src/app/_class/publication';
import { CommentService } from 'src/app/_services/comment.service';
import { PublicationService } from 'src/app/_services/publication.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-publication-public',
  templateUrl: './publication-public.component.html',
  styleUrls: ['./publication-public.component.css']
})
export class PublicationPublicComponent implements OnInit {

  //liste publication public 
  publications: Publication[] = [];
  comment: Comment = new Comment;
  
  publicationUpdate :Publication = new Publication;

  x : any;

  commentForm =  new FormGroup({
    contenu: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
    ])
  });

  constructor(private publicationService: PublicationService,
              private tokenStorage: TokenStorageService,
              private commentService : CommentService,
              private router:Router ) { }

  ngOnInit(): void {

    this.getPublicationsPublic();
  }


  //get all public 
  private getPublicationsPublic()
  {
    this.publicationService.getPublicationPublic().subscribe(data => {
      this.publications = data;
    });
  }

  //like 
  publicationLiked(publication: Publication)
  {
     let pub  = publication;

      pub.countLike ++;
     
      this.publicationService.likePublication(pub.id).subscribe(
        data => {
      
          this.publicationUpdate = data;
          this.getPublicationsPublic();
        }
      );

     
  }


  //getCommentsByPublication(id:number)
  getCommentsByPublication(id:number)
  {

    console.log("getCommentsByPublication " + id);
    this.x = document.getElementById(""+id);

    console.log(this.x.style.display);

    if (this.x.style.display == "none") {
      this.x.style.display = "block";
    } else {
      this.x.style.display = "none";
    }
  }


  //create comment by publication  
  createComment(id_publication:number)
  {
    
    this.comment.contenu = this.commentForm.controls['contenu'].value;
    this.comment.username = this.tokenStorage.getUser().username;
      console.log("comment  " , this.comment);


      this.commentService.createCommentByPublication(this.comment, id_publication)
                          .subscribe( data => {
                            console.log("data created " + data);
                          });

                          
       window.location.reload();                    

  }
  
}
