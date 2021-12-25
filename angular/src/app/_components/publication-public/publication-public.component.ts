import { Component, OnInit } from '@angular/core';
import { Publication } from 'src/app/_class/publication';
import { PublicationService } from 'src/app/_services/publication.service';

@Component({
  selector: 'app-publication-public',
  templateUrl: './publication-public.component.html',
  styleUrls: ['./publication-public.component.css']
})
export class PublicationPublicComponent implements OnInit {

  publications: Publication[] = [];

  constructor(private publicationService: PublicationService ) { }

  ngOnInit(): void {

    this.getPublicationsPublic();
  }


  private getPublicationsPublic()
  {
    this.publicationService.getPublicationPublic().subscribe(data => {
      this.publications = data;
    });
  }
}
