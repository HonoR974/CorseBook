import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { FileAPI } from 'src/app/_class/file-api';
import { User } from 'src/app/_class/user';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UploadS3Service } from 'src/app/_services/upload-s3.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'Accueil ';

  

  isLoggedIn = false;
  user: User = new User;
  jwt :any;

  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {

    //verification du jwt 
    //get info user 
    if (this.tokenStorage.getToken()) {
      this.user = this.tokenStorage.getUser();
      this.isLoggedIn = true;
      this.jwt = this.tokenStorage.getToken();
      
    }

 

  }

}