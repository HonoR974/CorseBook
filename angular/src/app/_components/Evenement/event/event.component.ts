import { Component, OnInit } from '@angular/core';
import { Marker } from 'src/app/_class/marker';
import { Location } from 'src/app/_class/location';
import { Router } from '@angular/router';
@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  

  constructor(private router : Router) { }



  ngOnInit() {
  }


  createEvent()
  {
    this.router.navigate(['create-event']);
  }



}
