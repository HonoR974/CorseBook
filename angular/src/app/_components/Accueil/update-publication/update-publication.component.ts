import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FileAPI } from 'src/app/_class/file-api';
import { Publication } from 'src/app/_class/publication';
import { PublicationService } from 'src/app/_services/publication.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UploadS3Service } from 'src/app/_services/upload-s3.service';

@Component({
  selector: 'app-update-publication',
  templateUrl: './update-publication.component.html',
  styleUrls: ['./update-publication.component.css'],
})
export class UpdatePublicationComponent implements OnInit {
  id!: number;
  currentUser: any;

  publication: Publication = new Publication();
  publicationSend: Publication = new Publication();

  files: FileAPI[] = [];

  listFilesAPI: FileAPI[] = [];

  myForm = new FormGroup({
    contenu: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
    ]),
    file: new FormControl(),
  });

  cheminImage: any = 'https://testp12.s3.eu-west-3.amazonaws.com/';

  fileAPI: FileAPI = new FileAPI();

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private publicationService: PublicationService,
    private uploadS3Service: UploadS3Service,
    private tokenStorage: TokenStorageService
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.getPublicationById(this.id);
  }

  getPublicationById(id: number) {
    this.publicationService.getPublicationById(id).subscribe((data) => {
      this.publication = data;
    });
  }

  updatePublicationByID() {
    this.publicationSend.contenu = this.myForm.controls['contenu'].value;
    this.publicationSend.listFile = this.listFilesAPI;

    console.log('  pub  ', this.publicationSend);

    this.publicationService
      .updatePublication(this.id, this.publicationSend)
      .subscribe(
        (data) => {
          console.log('\n  data ', data);
          this.goToPublicationPublic();
        },
        (error) => console.log(error)
      );
  }

  goToPublicationPublic() {
    this.router.navigate(['home']);
  }

  onFileChange(event: any) {
    for (var i = 0; i < event.target.files.length; i++) {
      this.files.push(event.target.files[i]);
    }
  }

  async newFileUpdate() {
    for (let i = 0; i < this.files.length; i += 1) {
      let file = this.files[i];
      let filePath = 'image/' + file.name;
      //s3
      try {
        console.log('request upload file ' + filePath);
        //s3
        let response = await this.uploadS3Service.uploadFileS3(file, filePath);
      } catch (error) {
        console.log("erreur lors de l'envoie de la publication");
      }

      //api
      this.fileAPI.name = file.name;
      this.fileAPI.url = this.cheminImage + filePath;

      console.log('fileAPI ajouté  ', this.fileAPI);

      this.listFilesAPI.push(this.fileAPI);
    }

    this.updatePublicationByID();
  }
}
