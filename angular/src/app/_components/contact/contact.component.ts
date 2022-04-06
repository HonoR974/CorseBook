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

  //liste demande envoyé   
  lUserInvited: User[] = [];

  //liste invitation recu 
  lDemandeInvitation: User[] = [];

  //lien de l'image par default des user 
  urlIconUser:any = "https://testp12.s3.eu-west-3.amazonaws.com/image/user.png";

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
      console.log("user image ", this.user);
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
        this.router.navigate(['chat',id]);
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
         this.ngOnInit();
        
       }
     );

  }

  //-----------List Demande Envoyé --------------//

  getListDemandeContact()
  {
    this.contactService.getUserAskList().subscribe ( data => 
      {
        this.lUserInvited = data;
      });

  }

  
  //la fonction fonctionne mais genere une 
  cancelDemande(id:any)
  {
    this.contactService.cancelDemande(id).subscribe( 
      data => 
      {
      
        this.lUserInvited = data;
      }
    );

  }


  //----------------- List Invitation Recu ----------------//

  
   getListDemandeInvitation()
   {
     this.contactService.getUserInvited().subscribe ( data => 
       {
   
         this.lDemandeInvitation = data;
       });
 
 
   }

  acceptDemand(id:any)
  {
    this.contactService.acceptDemand(id).subscribe( data => 
      {

        this.ngOnInit();
        window.location.reload();
      });
  }

  refuseDemande(id:any)
  {
    this.contactService.refuseDemande(id).subscribe( data => 
      {

        this.ngOnInit();
      });
  }

}
