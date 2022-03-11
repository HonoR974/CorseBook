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
    
  lat = 22.4064172;

  long = 69.0750171;


  markers = [

        {

            lat: 21.1594627,

            lng: 72.6822083,

            label: 'Surat',

            draggable : true

        },

        {

            lat: 23.0204978,

            lng: 72.4396548,

            label: 'Ahmedabad',

            draggable : false

        },

        {

            lat: 22.2736308,

            lng: 70.7512555,

            label: 'Rajkot',

            draggable : true

        }

    ];


    latitude: number;
    longitude: number;

    
  constructor() { }



  ngOnInit() {
    this.setCurrentLocation();
  }
// Get Current Location Coordinates
private setCurrentLocation() {
  if ('geolocation' in navigator) {
    navigator.geolocation.getCurrentPosition((position) => {
      this.latitude = position.coords.latitude;
      this.longitude = position.coords.longitude;
      this.zoom = 15;
    });
  }
}
   
  clickedMarker(label: string, index: number) {
    console.log(`clicked the marker: ${label || index}`)
  }
 

}