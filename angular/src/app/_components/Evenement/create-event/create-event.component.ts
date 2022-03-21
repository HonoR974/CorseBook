
import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl, Validators } from '@angular/forms';
import { Evenement } from 'src/app/_class/evenement';
import { Marker } from 'src/app/_class/marker';
import { EventService } from 'src/app/_services/event.service';

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.css']
})
export class CreateEventComponent implements OnInit {

  //FormControl
  eventForm = new FormGroup({

    name: new FormControl('', [
     Validators.required,
    Validators.minLength(3)
    ]),
    contenu: new FormControl('', [
   Validators.required,
      Validators.minLength(5)
    ]),
    dateDebut: new FormControl([
    Validators.required
    ]),
    dateFin: new FormControl([
    Validators.required
    ]),

    
  });

  event:Evenement;

  //agm 
  // google maps zoom level
  zoom: number = 8;

  // initial center position for the map
  lat: number = 41.91;
  lng: number =  8.73;

  markers: Marker[] = [];
  markerAdded: Marker;
  i: number = 0;

  constructor(private eventService: EventService ) { }

  ngOnInit(): void {
  }

  
  get f() {
    return this.eventForm.controls;
  }

  createEvent()
  {
    this.event = new Evenement();

    this.event.name = this.eventForm.value.name;
    this.event.dateDebut = this.eventForm.value.dateDebut;
    this.event.dateFin = this.eventForm.value.dateFin;
    this.event.contenu = this.eventForm.value.contenu;
    this.event.listMarker = this.markers;

    console.log("send data ", this.event);
    this.eventService.createEvent(this.event).subscribe( data => 
      {
        console.log("data " , data);
        this.ngOnInit();
      });


  }

  addMarker(lat:number, lng:number)
  { 

    this.markerAdded = new Marker(lat, lng);
    this.markerAdded.id = this.i++;
    this.markers.push(this.markerAdded);
  }

  markerDragEnd(id :number, lat:number, lng :number)
  {
    
    this.markerAdded = new Marker(lat, lng);
    this.markers.forEach(element => 
      {
        if (element.id === id)
        {
          element = this.markerAdded;
          this.markers[id] = element;
        }
      });
      
    
  }
}
