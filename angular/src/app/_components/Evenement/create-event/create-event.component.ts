import { Component, OnInit, ViewChild, ElementRef, NgZone } from '@angular/core';
import { MapsAPILoader } from '@agm/core';
import { FormGroup,FormControl, Validators } from '@angular/forms';
import { Evenement } from 'src/app/_class/evenement';
import { FileAPI } from 'src/app/_class/file-api';
import { Marker } from 'src/app/_class/marker';
import { EventService } from 'src/app/_services/event.service';
import { UploadS3Service } from 'src/app/_services/upload-s3.service';

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

    file: new FormControl(),
    
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


  //file 
  files: File[] = [];

  cheminImage: any =
  'https://testp12.s3.eu-west-3.amazonaws.com/';


  listFileAPI: FileAPI[] = [];
  fileAPI : FileAPI = new FileAPI();

  constructor(private eventService: EventService, 
              private uploadS3Service: UploadS3Service ) { }

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
    this.event.listFileAPI = this.listFileAPI;

    console.log("send date " , this.event.dateDebut);

    
    this.eventService.createEvent(this.event).subscribe( data => 
      {
        console.log("receved marker " , data);
        this.ngOnInit();
        window.location.reload();
      });


  }

  //------------ AGM 
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

  //------------- File 
  addFile(event:any)
  {
    for (var i = 0; i < event.target.files.length; i++) {
      this.files.push(event.target.files[i]);
    }
  }

  async fileUpdate()
  {
    for (let i = 0; i < this.files.length; i += 1) {

      let file = this.files[i];
      let filePath = "image/" + file.name;
      //s3
      try {
        //s3
        let response = await this.uploadS3Service.uploadFileS3(file, filePath);
     
      } catch (error) {
        console.log("erreur lors de l'envoie de la publication");
      }

      this.fileAPI = new FileAPI();
      //api
      this.fileAPI.name = file.name;
      this.fileAPI.url = this.cheminImage  + filePath;

      this.listFileAPI.push(this.fileAPI);
      
    }
     this.files = [];
     this.createEvent();
  }

}
