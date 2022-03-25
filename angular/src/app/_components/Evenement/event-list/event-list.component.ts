import { Component, OnInit } from '@angular/core';
import { Evenement } from 'src/app/_class/evenement';
import { EventService } from 'src/app/_services/event.service';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {

  events:Evenement[] = [];

//agm 
  // google maps zoom level
  zoom: number = 8;

  // initial center position for the map
  lat: number = 41.91;
  lng: number =  8.73;

  constructor(private eventService: EventService) { }

  ngOnInit(): void {
    this.getAllEvent();
  }

  getAllEvent()
  {
    this.eventService.getallEvent().subscribe( data => 
      {
        this.events = data;
        console.log("getEvent ", data);
      });
  }

  toggle(id:number)
  {
    console.log("event id " , id );
    
  }

}
