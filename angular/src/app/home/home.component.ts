import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { FileAPI } from '../_class/file-api';
import { User } from '../_class/user';
import { TokenStorageService } from '../_services/token-storage.service';
import { UploadS3Service } from '../_services/upload-s3.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 's3-img-upload';
  files: File[] = [];

  fileAPi : FileAPI = new FileAPI;
 
  renderImages: any = [];

  cheminImage:any = "https://testp12.s3.eu-west-3.amazonaws.com/images/aws.png";

  isLoggedIn = false;
  user: User = new User;
  jwt :any;

  constructor(private tokenStorage: TokenStorageService,
              private uploadS3Service: UploadS3Service,
              private toaster: ToastrService) { }

  ngOnInit(): void {

    if (this.tokenStorage.getToken()) {
      this.user = this.tokenStorage.getUser();
      this.isLoggedIn = true;
      this.jwt = this.tokenStorage.getToken();
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

      let filePath =
        'images/' + file.name; // to create unique name for avoiding being replaced

        let name:string = file.name;

        this.fileAPi.name = name;
        this.fileAPi.url = filePath;
      try {
   
        //api
        this.uploadS3Service.uploadFileAPI(this.fileAPi);

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
    this.files = [];
  }
}