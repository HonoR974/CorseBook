import {Component, OnInit} from '@angular/core';

import { UserService } from 'src/app/_services/user.service';



@Component({
  selector: 'app-list-nature',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {




  constructor(private userService:UserService)
  {}


  ngOnInit() {


      

  }


}
