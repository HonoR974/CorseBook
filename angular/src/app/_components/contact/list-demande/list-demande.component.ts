import { Component, OnInit } from '@angular/core';
import { ContactService } from 'src/app/_services/contact.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { User } from 'src/app/_class/user';
import { Router } from '@angular/router';
@Component({
  selector: 'app-list-demande',
  templateUrl: './list-demande.component.html',
  styleUrls: ['./list-demande.component.css']
})
export class ListDemandeComponent implements OnInit {


  user: User = new User;

  lUserInvited: User[] = [];


  constructor(private tokenStorage:TokenStorageService, 
    private contactService:ContactService,
    private router : Router) { }

  ngOnInit(): void
  {
    if (this.tokenStorage.getToken()) {
      this.user = this.tokenStorage.getUser();

      this.getListDemandeContact();
    }
  }


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

        this.refreshContactLis();
        this.ngOnInit();
      });

  }

  refreshContactLis()
  {
    let currentUrl = this.router.url;
    this.router.navigateByUrl('contact-list', {skipLocationChange: true}).then(() => {
        this.router.navigate([currentUrl]);
    });
  }


}
