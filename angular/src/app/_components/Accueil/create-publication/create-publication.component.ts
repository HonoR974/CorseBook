import { Component, OnInit } from '@angular/core';

import { ToastrService } from 'ngx-toastr';
import {
  FormGroup,
  Validators,
  FormControl,
  FormArray,
  FormBuilder,
} from '@angular/forms';

import { FileAPI } from 'src/app/_class/file-api';
import { Publication } from 'src/app/_class/publication';
import { PublicationService } from 'src/app/_services/publication.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UploadS3Service } from 'src/app/_services/upload-s3.service';
import { Marker } from 'src/app/_class/marker';

@Component({
  selector: 'app-create-publication',
  templateUrl: './create-publication.component.html',
  styleUrls: ['./create-publication.component.css'],
})
export class CreatePublicationComponent implements OnInit {

  

  currentUser: any;
  isLoggedIn = false;

  files: File[] = [];


  isLoading:boolean = false;
  cheminImage: any =
    'https://testp12.s3.eu-west-3.amazonaws.com/';

  myFiles: string[] = [];

  myForm = new FormGroup({
    contenu: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
    ]),
    file: new FormControl(),
  });

    //AGM 

    // initial center position for the map
    lat: number = 41.91;
    lng: number =  8.73;

    markers: Marker[] = [];
    markerAdded: Marker;
    i:number = 0;

  publication: Publication = new Publication();

  fileAPI: FileAPI = new FileAPI();

  constructor(
    private tokenStorage: TokenStorageService,
    private publicationService: PublicationService,
    private uploadS3Service: UploadS3Service
  ) {}

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
    }
  }

  get f() {
    return this.myForm.controls;
  }

  onFileChange(event: any) {

    for (var i = 0; i < event.target.files.length; i++) {
      this.files.push(event.target.files[i]);
    }
  }

  async newFileUpdate() {

    for (let i = 0; i < this.files.length; i += 1) {

      let file = this.files[i];
      let filePath = "image/" + file.name;
      //s3
      try {
        this.isLoading = true;
        //s3
        let response = await this.uploadS3Service.uploadFileS3(file, filePath);
        console.log("response " +  response);
        
      } catch (error) {
        console.log("erreur lors de l'envoie de la publication");
      }

      this.fileAPI = new FileAPI();
      //api
      this.fileAPI.name = file.name;
      this.fileAPI.url = this.cheminImage  + filePath;

      this.publication.listFile.push(this.fileAPI);
    }
     this.files = [];

    this.createPubAPI();

  }


  createPubAPI()
  {


    //le contenu 
 
    this.publication.contenu = this.myForm.controls['contenu'].value;
    
    //l'user 
    this.currentUser = this.tokenStorage.getUser();
    this.publication.username = this.currentUser.username;

    //marker 
    this.publication.listMarker = this.markers;

    console.log("before created ", this.publication);
    console.log("before created marker  " ,  this.publication.listMarker);


    this.publicationService
      .createPublication(this.publication)
      .subscribe( data  => {
        console.log("data created " , data);
        window.location.reload();
      });
     
  }

  //---------- AGM 

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
