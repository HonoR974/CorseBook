import { Component, OnInit } from '@angular/core';
import { User } from '../_class/user';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  
  isLoggedIn = false;
  user: User = new User;
  jwt :any;

  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {

    if (this.tokenStorage.getToken()) {
      this.user = this.tokenStorage.getUser();
      this.isLoggedIn = true;
      this.jwt = this.tokenStorage.getToken();
    }
  }
}