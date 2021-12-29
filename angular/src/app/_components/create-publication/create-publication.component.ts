import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FormGroup, Validators, FormControl, FormArray, FormBuilder } from '@angular/forms';

import { FileAPI } from 'src/app/_class/file-api';
import { Publication } from 'src/app/_class/publication';
import { PublicationService } from 'src/app/_services/publication.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UploadS3Service } from 'src/app/_services/upload-s3.service';

@Component({
  selector: 'app-create-publication',
  templateUrl: './create-publication.component.html',
  styleUrls: ['./create-publication.component.css']
})
export class CreatePublicationComponent implements OnInit {

  isLoggedIn = false;

  files: File[] = [];
  renderImages: any = [];

  cheminImage:any = "https://testp12.s3.eu-west-3.amazonaws.com/images/aws.png";


  myFiles:string [] = [];
  
  myForm = new FormGroup({
   contenu: new FormControl('', [Validators.required, Validators.minLength(3)]),
   file: new FormControl(),
  });


  publication: Publication = new Publication;

  fileAPI: FileAPI = new FileAPI;

  constructor( private tokenStorage: TokenStorageService,
                private publicationService: PublicationService,
                private uploadS3Service: UploadS3Service,
                private toaster: ToastrService,
   ) {}



  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
    }
  }
   
  onSelect(event: any) {
    this.files.push(...event.addedFiles);
  }

  onRemove(event: any) {
    this.files.splice(this.files.indexOf(event), 1);
  }

  async onImageUpdate() {

    if (this.files.length < 1) {
      this.toaster.error('Please Select Drop your Image first');
      return;
    }

    for (let i = 0; i < this.files.length; i += 1) {
      let file = this.files[i];

      let filePath = 'images/' + file.name; 

      try {
   
        //s3
        let response = await this.uploadS3Service.uploadFileS3(file, filePath);
        console.log(response);

        this.toaster.success(file.name + 'uploaded Successfully :)');
        const url = (response as any).Location;
        this.renderImages.push(url);
        
      
      } catch (error) {
        this.toaster.error('Something went wrong! ');
      }

    }
  
  }


      
  get f(){
    return this.myForm.controls;
  }
     
  onFileChange(event:any) {
   
        for (var i = 0; i < event.target.files.length; i++) { 
            this.myFiles.push(event.target.files[i]);
            this.files.push(event.target.files[i]);
        }
  }
      
  submit(){

     this.newFileUpdate();

     this.publication.contenu = this.myForm.controls['contenu'].value;

     console.log("publication " , this.publication);
 
     this.publicationService.createPublication(this.publication).subscribe( 
       data => 
       {
         console.log(data);
       }
     );
  
     window.location.reload();
      }

  async newFileUpdate()
  {

   

    for (let i = 0; i < this.files.length; i += 1) {
          let file = this.files[i];
          let filePath = 'images/' + file.name; 

          //s3
      try {
        
            //s3
            let response = this.uploadS3Service.uploadFileS3(file, filePath);
            console.log(response);
        } catch (error) {
          
          console.log("erreur lors de l'envoie de la publication");
        }

        //api
        this.fileAPI.name = file.name;
        this.fileAPI.url = this.cheminImage+ "/" + filePath;
        this.fileAPI.type = file.type;

        console.log("fileAPI" + this.fileAPI);

        this.publication.file.push(this.fileAPI);

    }



  }



}
