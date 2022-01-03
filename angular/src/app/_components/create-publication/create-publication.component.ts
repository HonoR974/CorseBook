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

@Component({
  selector: 'app-create-publication',
  templateUrl: './create-publication.component.html',
  styleUrls: ['./create-publication.component.css'],
})
export class CreatePublicationComponent implements OnInit {


  currentUser: any;
  isLoggedIn = false;

  files: File[] = [];
  renderImages: any = [];

  cheminImage: any =
    'https://testp12.s3.eu-west-3.amazonaws.com/images/aws.png';

  myFiles: string[] = [];

  myForm = new FormGroup({
    contenu: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
    ]),
    file: new FormControl(),
  });

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
      let filePath = 'images/' + file.name;

      //s3
      try {

        //s3
        let response = await this.uploadS3Service.uploadFileS3(file, filePath);
        console.log("response upload file " + response);


      } catch (error) {
        console.log("erreur lors de l'envoie de la publication");
      }

      //api
      this.fileAPI.name = file.name;
      this.fileAPI.url = this.cheminImage + '/' + filePath;

      console.log('fileAPI updated ' + this.fileAPI);

      this.publication.listFile.push(this.fileAPI);
    }
     this.files = [];

    this.createPubAPI(this.publication);

  }


  createPubAPI(publicationRequest : Publication)
  {

    //le contenu 
    console.log('publicationRequest', publicationRequest);
    this.publication.contenu = this.myForm.controls['contenu'].value;
    
    //l'user 
    this.currentUser = this.tokenStorage.getUser();
    this.publication.username = this.currentUser.username;

    
    console.log('publication ', this.publication);

    this.publicationService
      .createPublication(this.publication)
      .subscribe((data) => {
        console.log("data created " + data);
      });

      window.location.reload();

  }


}