import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSliderModule } from '@angular/material/slider';
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

  //------------------ Publication ----------------//

  //get all public 
  private getPublicationsPublic()
  {
    this.publicationService.getPublicationPublic().subscribe(data => {
      this.publications = data;
    });
  }

  //like 
  publicationLiked(id: number)
  {

     
      this.publicationService.likePublication(id).subscribe(
        data => {
      
          this.publicationUpdate = data;
          this.getPublicationsPublic();
        }
      );
  }

  //dislike
  publicationDisliked(id: number)
  {
    this.publicationService.dislikedPublication(id).subscribe(
      data => {
    
        this.publicationUpdate = data;
        this.getPublicationsPublic();
      }
    );

  }


  //update 
  updatePublication(id:number)
  {
    this.router.navigate(['update-publication',id]);
  }

  //delete 
  clickMethod(id: number) {
    if(confirm("Are you sure to delete "+id)) {
      console.log("Implement delete functionality here");
    }
    else
    {
      console.log("Cancel delete ");
    }
  }

  //----------------- Comments  ---------------//

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

  commentLiked(id:number)
  {
    this.commentService.commentLikedById(id)
                      .subscribe ( data => 
                        {
                          console.log("data created " + data );
                        });

    this.getPublicationsPublic();
  }

  
  
}
