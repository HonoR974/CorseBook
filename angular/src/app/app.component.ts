import { Component } from '@angular/core';
import { User } from './_class/user';
import { TokenStorageService } from './_services/token-storage.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'Angular & Spring Boot ';

  icon ="https://testp12.s3.eu-west-3.amazonaws.com/image/kenny.jpg";

  isLoggedIn = false;
  user:User;




  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {

    if (this.tokenStorageService.getToken()) {
      this.isLoggedIn = true;
      this.user = this.tokenStorageService.getUser();
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }


 

}
