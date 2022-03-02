import { Component } from '@angular/core';
import { User } from './_class/user';
import { TokenStorageService } from './_services/token-storage.service';
import { UserService } from './_services/user.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'Angular & Spring Boot ';

  icon ="https://testp12.s3.eu-west-3.amazonaws.com/image/corsica.png";
  

  isLoggedIn = false;
  user: User = new User;




  constructor(private tokenStorageService: TokenStorageService,
               private userService: UserService) { }

  ngOnInit(): void {

    if (this.tokenStorageService.getToken()) {
      this.isLoggedIn = true;
      this.user = this.tokenStorageService.getUser();
      this.getUserByUsername();
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }


  getUserByUsername()
  {
    this.userService.getUserByUsername(this.user.username).subscribe ( 
      data => 
      {
        this.user  = data;
        console.log("data ", data);
      }
    );
  }
 

}
