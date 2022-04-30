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
    private _formBuilder: FormBuilder
    ) { }


  ngOnInit(): void {

    this.userService.getAllUsername().subscribe(data => 
      {
        this.users = data;
      });
    

      this.userFilter = this.secondControl.valueChanges.pipe(
        startWith(''),
        map(value => this.secondFilter(value))
      );

      if(this.tokenStorageService.getToken())
      {
        this.isLoggedIn = true;
        this.user = this.tokenStorageService.getUser();
        this.getUserByUsername(this.user.username);
      }
  }

  
  logout(): void {
    this.tokenStorageService.signOut();
    this.accueil();
  }

  accueil()
  {
    this.router.navigate(['home']);
  }


  getUserByUsername(username:string)
  {
    this.userService.getUserByUsername(username).subscribe ( 
      data => 
      {
        this.user  = data;

      }
    );
  }


  search()
  { 
    this.userService.getUserByUsername(this.secondControl.value).subscribe ( 
      data => 
      {
        this.user  = data;

        this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
          this.router.navigate(['profile/',this.user.id]);
      });
      }
    );
  }
  
  private secondFilter(value:string):string[]
  {
      const filterValue = value.toLowerCase();

      return this.users.filter( user => user.toLowerCase()
      .indexOf(filterValue) ===0 );

  }


}
