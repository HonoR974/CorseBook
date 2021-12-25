import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Publication } from 'src/app/_class/publication';
import { PublicationService } from 'src/app/_services/publication.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-create-publication',
  templateUrl: './create-publication.component.html',
  styleUrls: ['./create-publication.component.css']
})
export class CreatePublicationComponent implements OnInit {

  isLoggedIn = false;
  disconnected = true;

  publication: Publication = new Publication();

  constructor( private tokenStorage: TokenStorageService,
                private publicationService: PublicationService,
                private router: Router) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.disconnected = false;
    }

  }


  onSubmit()
  {

    
    this.publicationService.createPublication(this.publication).subscribe(
      data => { 
        console.log("publication créer " + data);
        this.reloadPage();
      }
    );
  }

  reloadPage() : void
  {
    console.log("reload");

    window.location.reload();

  }
}
