import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import 'sweetalert2/src/sweetalert2.scss'

import Swal from 'sweetalert2'
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

  name = 'Angular';

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





  //------- Alert -------------//


  //delete 
  deletePublication(id : number){


    Swal.fire({
      title: 'Voulez-vous vraiment supprimer votre publication ?',
      text: 'Vous ne pourrez plus récupér votre publication',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Oui, je la supprime',
      cancelButtonText: 'Non, je la garde '
    }).then((result) => {
      if (result.value) {
        Swal.fire(
          'Deleted!',
          'Votre publication a été supprimée.',
          'success'
        );

        this.publicationService.deletePublication(id).subscribe( data => {
          console.log(data);
          this.getPublicationsPublic();
        });

      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire(
          'Cancelled',
          'Votre publication est toujours là :D ',
          'error'
        );
      }
    })
  }
  
  
}
