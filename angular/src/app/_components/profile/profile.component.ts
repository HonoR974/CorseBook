import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/_class/user';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';
import { AuthService } from 'src/app/_services/auth.service';
import { ContactService } from 'src/app/_services/contact.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User = new User;
  currentUser:any;

  id!:number;

  isContact = false;

  constructor(private token: TokenStorageService,
              private route: ActivatedRoute,
              private userService: UserService,
              private authService: AuthService,
              private contactService: ContactService,
              private router: Router
               ) 
  { }

  ngOnInit(): void {

    this.getUserById();

    if(this.token.getToken())
    {
      this.currentUser=this.token.getUser()

      const username= this.currentUser.username;
      this.getUserByUsername(username);

    
    }
  }



  getUserById()
  {
   
    this.id = this.route.snapshot.params['id'];
    
    this.userService.getUserById(this.id).subscribe ( data => 
      {

        this.user = data;
      });
  }

  getUserByUsername(username:string)
  {
    this.userService.getUserByUsername(username).subscribe( data => 
      {
        this.currentUser = data;

        this.checkIfContact();
      });

  }

  checkIfContact()
  {
    this.isContact = false;
    for (var userTest of this.currentUser.listContact)
    {
   

      if(userTest.username === this.user.username)
      {
        this.isContact = true;
      }
    }
  }

  deleteContact(id:any)
  {
    this.contactService.deleteContact(id).subscribe(data => 
      {
        console.log("data ", data);
        window.location.reload();
      });
  }

 

}