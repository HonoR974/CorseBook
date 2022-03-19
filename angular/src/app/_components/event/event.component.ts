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

  title = 'My first AGM project';

  // google maps zoom level
  zoom: number = 8;
 
  // initial center position for the map
  lat: number = 41.91;
  lng: number =  8.73;

  markers = [
    {
      
        lat: 41.54,
        lng: 8.43,
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
            ];
    
  location !: Location ;
  selectedMarker !: Marker;


  constructor(private router : Router) { }



  ngOnInit() {
   this.selectedMarker = new Marker(41.50,8.50);
   this.location = new Location(41.50, 7.50);
  }

   
  clickedMarker(label: string, latitude: number, longitude : number) {
    console.log(`clicked the marker: ` , label + "\n latitude  : " , latitude + "\n longitude : ", longitude);
  }
 

  
  addMarker(lat: number, lng: number) {
    console.log("add markers " )
    this.markers.push({
        lat,
        lng,
        label:"new",
        draggable: true
    })
  }

  selectMarker(event:any) {
   
    this.selectedMarker = new Marker(event.latitude,event.longitude);
    
    console.log("selectMarker", this.selectedMarker);
}

  markerDragEnd(coords: any) {
    
    console.log("coords ", coords);
    this.location.latitude = coords.lat;
    this.location.longitude = coords.lng;
  }

  createEvent()
  {
    this.router.navigate(['create-event']);
  }



}
