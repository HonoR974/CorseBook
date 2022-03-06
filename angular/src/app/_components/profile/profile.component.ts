import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  Validators,
  FormControl,
  FormArray,
  FormBuilder,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/_class/user';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';
import { AuthService } from 'src/app/_services/auth.service';
import { ContactService } from 'src/app/_services/contact.service';
import { FileAPI } from 'src/app/_class/file-api';
import { UploadS3Service } from 'src/app/_services/upload-s3.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User = new User;
  currentUser:any;

  id!:number;

  isContact = false;

  //profile picture 

  cheminImage: any =
    'https://testp12.s3.eu-west-3.amazonaws.com/';

    myForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      file: new FormControl('', [Validators.required]),
      fileSource: new FormControl('', [Validators.required])
    });

  fileAPI: FileAPI = new FileAPI();

  file!:File ;

  constructor(private token: TokenStorageService,
              private route: ActivatedRoute,
              private userService: UserService,
              private uploadS3Service: UploadS3Service,
              private contactService: ContactService,
     
               ) 
  { }

  ngOnInit(): void {

    this.getUserById();

    if(this.token.getToken())
    {
      this.currentUser=this.token.getUser()

      const username= this.currentUser.username;
      this.getUserByUsername(username);

    }
  }



  getUserById()
  { 
    this.id = this.route.snapshot.params['id'];
  
    this.userService.getUserById(this.id).subscribe ( data => 
      {
        this.user = data;
        
      });
  }

  getUserByUsername(username:string)
  {
    this.userService.getUserByUsername(username).subscribe( data => 
      {
        this.currentUser = data;

        this.checkIfContact();
      });

  }

  checkIfContact()
  {
    let username = this.user.username;

    for( let userTest of Object.values(this.currentUser.listContact))
    {
      console.log("username ", username);
      console.log(userTest);
    }

    console.log("check contact  ", this.isContact);
  }

  deleteContact(id:any)
  {
    this.contactService.deleteContact(id).subscribe(data => 
      {
    
        
        window.location.reload();
      });
  }

 
  //------------- Profile Picture 
  

  get f() {
    return this.myForm.controls;
  }

  onFilePicture(event:any)
  {
  
    if (event.target.files.length > 0) {
      const file = event.target.files[0];


      let filePath = "image/" + file.name;

      this.file = file;

      this.fileAPI.name = file.name;
      this.fileAPI.url = this.cheminImage + filePath;
    }

  }
  

   async updateProfilePicture() {
   

      //s3
      try {
        //s3
        let response = await this.uploadS3Service.uploadFileS3(this.file, this.fileAPI.name);
        console.log("response : " , response);

      } catch (error) {
        console.log("erreur lors du telechargement de l'image ");
      }

      this.updateAPI();
    }

  
    //une fois le telechargement finit 
    //l'api rectoi la modif 
    //et l'affiche dans la page profile 
    updateAPI()
    {
      this.userService.updateProfilePicture(this.fileAPI).subscribe( 
        data => 
        {
          this.currentUser = data;
          this.ngOnInit();
        }
      )
    }



}