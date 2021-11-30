import { Component, OnInit } from '@angular/core';
import { User } from '../_class/user';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  content?: string;
  username?:string;
  user: User;

  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    var user = this.tokenStorage.getUser();
    console.log("user " + user);

    this.username = user.username;
    console.log("username "+  this.username);

    this.content = user;
  }
}