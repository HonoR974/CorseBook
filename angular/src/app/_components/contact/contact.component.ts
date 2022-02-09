import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { User } from 'src/app/_class/user';
import { ContactService } from 'src/app/_services/contact.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {


  //list Contact 
  user: User = new User;

  listContact: User[] = [];

  //liste demande   

  lUserInvited: User[] = [];

  //liste invitation 
  lDemandeInvitation: User[] = [];


  constructor(private tokenStorage:TokenStorageService, 
    private contactService:ContactService,
    private router : Router) { }



  ngOnInit(): void {

    if (this.tokenStorage.getToken()) {
      this.user = this.tokenStorage.getUser();

      this.getListContact();
    }

  }

    
  //-------------- List Contact --------------------//
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

  //--------------List demande --------------------//


  getListDemandeContact()
  {
    this.contactService.getUserAskList().subscribe ( data => 
      {
        this.lUserInvited = data;
      });

  }

  acceptDemand(id:any)
  {
    this.contactService.acceptDemand(id).subscribe ( data => 
      {
        this.ngOnInit();
      });

  }



  getListDemandeInvitation()
  {
    this.contactService.getUserInvited().subscribe ( data => 
      {
        this.lDemandeInvitation = data;
      });


  }


}
