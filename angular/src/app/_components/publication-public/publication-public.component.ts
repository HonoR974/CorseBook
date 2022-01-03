import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Publication } from 'src/app/_class/publication';
import { PublicationService } from 'src/app/_services/publication.service';

@Component({
  selector: 'app-publication-public',
  templateUrl: './publication-public.component.html',
  styleUrls: ['./publication-public.component.css']
})
export class PublicationPublicComponent implements OnInit {

  publications: Publication[] = [];

  publicationUpdate :Publication = new Publication;

  constructor(private publicationService: PublicationService,
              private router:Router ) { }

  ngOnInit(): void {

    this.getPublicationsPublic();
  }


  private getPublicationsPublic()
  {
    this.publicationService.getPublicationPublic().subscribe(data => {
      this.publications = data;
    });
  }

  
  publicationLiked(publication: Publication)
  {
     let pub  = publication;

      pub.countLike ++;

      console.log("like " + pub.countLike);
      this.publicationService.likePublication(pub.id).subscribe(
        data => {
          console.log("data " + data);
          this.publicationUpdate = data;
          this.getPublicationsPublic();
        }
      );

     
  }

}
