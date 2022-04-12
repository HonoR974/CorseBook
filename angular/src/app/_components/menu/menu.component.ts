import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { map, Observable, startWith } from 'rxjs';
import { User } from 'src/app/_class/user';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';



@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {




  icon ="https://testp12.s3.eu-west-3.amazonaws.com/image/corsica.png";
  

  isLoggedIn = false;
  user: User = new User;

    //user search 
    secondControl = new FormControl();
    users:string[] = [];
    userFilter: Observable<string[]>;

  constructor(private tokenStorageService: TokenStorageService,
    private userService: UserService,
    private router: Router,
    private _formBuilder: FormBuilder) { }

  ngOnInit(): void {

    

    this.userService.getAllUsername().subscribe(data => 
      {
        console.log("data username", data );
        this.users = data;
      });
    

      this.userFilter = this.secondControl.valueChanges.pipe(
        startWith(''),
        map(value => this.secondFilter(value))
      );

  }

  
  logout(): void {
    this.tokenStorageService.signOut();
    this.accueil();
  }

  accueil()
  {
    this.router.navigate(['home']);
  }


  getUserByUsername()
  {
    this.userService.getUserByUsername(this.user.username).subscribe ( 
      data => 
      {
        this.user  = data;
      }
    );
  }


  test()
  { 

    console.log("test 2", this.secondControl.value);
  }
  
  private secondFilter(value:string):string[]
  {
    console.log("filter second ",value);

      const filterValue = value.toLowerCase();

      console.log("return "  + this.users.filter( user => user.toLowerCase()
      .indexOf(filterValue) ===0 ));


      return this.users.filter( user => user.toLowerCase()
      .indexOf(filterValue) ===0 );

  }


}
