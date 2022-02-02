import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/_class/user';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User = new User;
  id!:number;

  constructor(private token: TokenStorageService,
              private route: ActivatedRoute,
              private userService: UserService
               ) { }

  ngOnInit(): void {

    this.getCurrentUser();
  }


  getCurrentUser()
  {
    this.id = this.route.snapshot.params['id'];

    this.userService.getUserById(this.id).subscribe ( data => 
      {
        this.user = data;
        console.log("profile de l'user " , this.user);
      });
  }
}