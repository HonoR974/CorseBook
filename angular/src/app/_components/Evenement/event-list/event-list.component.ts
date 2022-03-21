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

  constructor(private eventService: EventService) { }

  ngOnInit(): void {
    this.getAllEvent();
  }

  getAllEvent()
  {
    this.eventService.getallEvent().subscribe( data => 
      {
        this.events = data;
      });
  }
}
