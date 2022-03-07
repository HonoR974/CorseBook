import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Publication } from 'src/app/_class/publication';
import { ContactService } from 'src/app/_services/contact.service';
import { PublicationService } from 'src/app/_services/publication.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';




@Component({
  selector: 'app-publication-profile',
  templateUrl: './publication-profile.component.html',
  styleUrls: ['./publication-profile.component.css']
})
export class PublicationProfileComponent implements OnInit {

  publications:Publication[] = [];
  id:any;

  constructor(private publicationService: PublicationService,
              private contactService : ContactService,
              private tokenStorage: TokenStorageService,
              private router:Router,
              private route: ActivatedRoute,) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.getPublicationByUserId();
  }

  getPublicationByUserId()
  {
    this.publicationService.getPublicationByUserID(this.id).subscribe( 
      data => 
      {
        this.publications = data;
      }
    );
  }



}
