import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Publication } from '../_class/publication';
import { PublicationService } from '../_services/publication.service';

@Component({
  selector: 'app-publication',
  templateUrl: './publication.component.html',
  styleUrls: ['./publication.component.css']
})
export class PublicationComponent implements OnInit {

  publications: Publication[] = [];


  constructor(private publicationService: PublicationService, private router: Router) { }

  ngOnInit(): void {
    this.getPublicationPublic();
  }

  private getPublicationPublic()
  {
    this.publicationService.getPublicationPublic().subscribe(data => {
      this.publications = data;
    });
  }

}
