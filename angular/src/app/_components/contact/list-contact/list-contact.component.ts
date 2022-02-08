import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { User } from 'src/app/_class/user';
import { ContactService } from 'src/app/_services/contact.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-list-contact',
  templateUrl: './list-contact.component.html',
  styleUrls: ['./list-contact.component.css']
})
export class ListContactComponent implements OnInit {

  user: User = new User;

  listContact: User[] = [];

  constructor(private tokenStorage:TokenStorageService, 
                private contactService:ContactService,
                private router : Router) { }

  ngOnInit(): void {

    if (this.tokenStorage.getToken()) {
      this.user = this.tokenStorage.getUser();

      this.getListContact();
    }


  }

  getListContact()
  {
      this.contactService.getContactList().subscribe(
        data => 
        {
          this.listContact = data;
        });
      
  }

  getContact(id:any)
  {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
        this.router.navigate(['profile/', id]);

    });

  }


}
