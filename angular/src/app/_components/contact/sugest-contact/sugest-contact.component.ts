import { Component, OnInit } from '@angular/core';
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

  constructor(private contactService: ContactService,
              private tokenService : TokenStorageService) { }

  ngOnInit(): void {

    if(this.tokenService.getToken())
    {
      this.getSuggestContact();
    }


  }

  getSuggestContact()
  {
    this.contactService.getSuggestContact().subscribe( data => 
      {
        this.listSuggest = data;
        console.log("list de sugggestion ", this.listSuggest);
      });

  }

}
