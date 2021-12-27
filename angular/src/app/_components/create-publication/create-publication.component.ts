import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
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
  disconnected = true;

  files: File[] = [];

  filesAPi: FileAPI[] = [];

  renderImages: any = [];

  cheminImage:any = "https://testp12.s3.eu-west-3.amazonaws.com/images/aws.png";


  publication: Publication = new Publication();

  constructor( private tokenStorage: TokenStorageService,
                private publicationService: PublicationService,
                private uploadS3Service: UploadS3Service,
                private toaster: ToastrService,
                private router: Router) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.disconnected = false;
    }

  }


  onSubmit()
  {

    
    this.publicationService.createPublication(this.publication).subscribe(
      data => { 
        console.log("publication créer " + data);
        this.reloadPage();
      }
    );
  }

  reloadPage() : void
  {
    console.log("reload");

    window.location.reload();

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
    this.files = [];
  }

}
