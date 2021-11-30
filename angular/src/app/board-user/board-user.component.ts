import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';


@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {

  content?: string;
  constructor(private authService: AuthService) { }

  ngOnInit(): void {

    /*ne doit pas retourer getAdminBoard
      mais recupere les infos sauvegardé par le tokenStorage dans register.ts */
      
    this.authService.getUserByJwt().subscribe(
      data => {
        this.content = data;
        console.log("content " + this.content);
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }

}
