import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  title = 'My first AGM project';
  // google maps zoom level
  zoom: number = 8;
 
  // initial center position for the map
  lat: number = 51.673858;
  lng: number = 7.815982;

  markers = [
    {
        lat: 51.673858,
        lng: 7.815982,
        label: "A",
        draggable: true
    },
    {
        lat: 51.373858,
        lng: 7.215982,
        label: "B",
        draggable: false
    },
    {
        lat: 51.723858,
        lng: 7.895982,
        label: "C",
        draggable: true
    }
            ]

    
  constructor() { }



  ngOnInit() {
   
  }

    
  clickedMarker(label: string, index: number) {
    console.log(`clicked the marker: ${label || index}`)
  }
 

}