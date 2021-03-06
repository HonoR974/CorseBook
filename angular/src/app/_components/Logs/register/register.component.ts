import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/_services/auth.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {
  form: any = {
    username: null,
    email: null,
    password: null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService, 
              private tokenStorage: TokenStorageService,
              private router:Router) { }

  ngOnInit(): void {
    if(this.tokenStorage.getToken())
    {
      this.accueil();
    }
  }

  onSubmit(): void {
    const { username, email, password } = this.form;

    this.authService.register(username, email, password).subscribe(
      data => {
   
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.authenticate();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }


  authenticate():void {

    const { username, password } = this.form;


    this.authService.login(username, password).subscribe( 
      data => {
        this.tokenStorage.saveToken(data.jwt);
        this.tokenStorage.saveUser(data);

        window.location.reload();
      }, 
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );

  }

  

  accueil()
  {
    this.router.navigate(['home']);
  }
}