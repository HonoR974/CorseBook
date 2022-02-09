import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/_class/user';
import { ContactService } from 'src/app/_services/contact.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-sugest-contact',
  templateUrl: './sugest-contact.component.html',
  styleUrls: ['./sugest-contact.component.css']
})
export class SugestContactComponent implements OnInit {

  listSuggest: User[] = [];
	
	responsiveOptions : any;
  constructor(private contactService: ContactService,
              private tokenService : TokenStorageService,
              private router: Router) {
               
               }

  ngOnInit(): void {

      this.getSuggestContact();


  }


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




}
