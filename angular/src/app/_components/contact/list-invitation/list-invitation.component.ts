import { Component, OnInit } from '@angular/core';
import { ContactService } from 'src/app/_services/contact.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { User } from 'src/app/_class/user'
@Component({
  selector: 'app-list-invitation',
  templateUrl: './list-invitation.component.html',
  styleUrls: ['./list-invitation.component.css']
})
export class ListInvitationComponent implements OnInit {



  
  user: User = new User;

  lDemandeInvitation: User[] = [];

  constructor(private tokenStorage:TokenStorageService, 
    private contactService:ContactService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.user = this.tokenStorage.getUser();

      this.getListDemandeInvitation();
    }
  }

  getListDemandeInvitation()
  {
    this.contactService.getUserInvited().subscribe ( data => 
      {
        this.lDemandeInvitation = data;
      });


  }
}
