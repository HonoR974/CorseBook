import { Component, OnInit } from '@angular/core';
import { Evenement } from 'src/app/_class/evenement';
import { User } from 'src/app/_class/user';
import { EventService } from 'src/app/_services/event.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  user:User;

  eventsUser:Evenement[] = [];


  constructor(private tokenStorageService: TokenStorageService,
              private eventService:EventService)
   { }

  ngOnInit(): void {
    this.user =  this.tokenStorageService.getUser();
    this.getEventsByUser();
  }


  getEventsByUser()
  {
    this.eventService.getAllEventByUser().subscribe( data => 
      {
        this.eventsUser = data;
        console.log("event by user data " , data);
      });
  }
 
  //get last event 

}
