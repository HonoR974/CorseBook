import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { FileAPI } from 'src/app/_class/file-api';
import { UploadS3Service } from 'src/app/_services/upload-s3.service';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {

  
  title = 's3-img-upload';
  files: File[] = [];

  filesAPi: FileAPI[] = [];
 
  renderImages: any = [];

  cheminImage:any = "https://testp12.s3.eu-west-3.amazonaws.com/images/";


  constructor(private uploadS3Service: UploadS3Service,
              private toaster: ToastrService) { }

  ngOnInit(): void {

      //get liste fileAPI 
      this.getAllFileAPI();
  }

  private getAllFileAPI()
  {
    this.uploadS3Service.getAllFileAPI().subscribe(data => 
      {
        this.filesAPi = data;
      })
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