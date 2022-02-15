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


  //suggest  
  listSuggest: User[] = [];

  responsiveOptions : any;
  constructor(private tokenStorage:TokenStorageService, 
    private contactService:ContactService,
    private router : Router) { }



  ngOnInit(): void {

    if (this.tokenStorage.getToken()) {
      this.user = this.tokenStorage.getUser();

      this.getListContact();
      this.getListDemandeContact();
      this.getListDemandeInvitation();
      this.getSuggestContact();
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

    //btn profile 
    getContact(id:any)
    {
      this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
          this.router.navigate(['profile/', id]);

      });
    }

    //btn chat 
    contact(id:any)
    {
      this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
        this.router.navigate(['chat']);

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




  getListDemandeInvitation()
  {
    this.contactService.getUserInvited().subscribe ( data => 
      {
        this.lDemandeInvitation = data;
      });


  }


  //-------------- Suggest ------------//

  
  //Suggest List 
  getSuggestContact()
  {
    this.contactService.getSuggestContact().subscribe( data => 
      {
        

        this.listSuggest = data;
      });
  }

  //Btn Profile 
  getUserProfil(id:number)
  {
    this.router.navigate(['profile/', id]);
  }


  //Btn Add User 
  addUser(id:number)
  {
     this.contactService.addContact(id).subscribe(
       data => 
       {
      //   this.refreshContactLis();
         this.ngOnInit();

       }
     );

     
  }



  //----------------- List Invitation ----------------//
  acceptDemand(id:any)
  {
    this.contactService.acceptDemand(id).subscribe( data => 
      {
        console.log("accepete Demande " , data);
        this.ngOnInit();
      });
  }

  refuseDemande(id:any)
  {
    this.contactService.refuseDemande(id).subscribe( data => 
      {
        console.log("refuse demande ", data);
        this.ngOnInit();
      });
  }

}
