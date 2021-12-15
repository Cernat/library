import { HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { BookApiService } from '../services/book-api.service';
import { UsernameValidators } from './username.validators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form = new FormGroup({
    username: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      UsernameValidators.cannotContainSpace
    ]),
    password: new FormControl('', Validators.required)
  });
  

  constructor(
    private bookApi: BookApiService,
    private authService: AuthenticationService,
    private router: Router
    ) { }

  ngOnInit(): void {
  }

  get username() {
    return this.form.get('username');
  }

  login() {

    this.authService.authenticate(this.form.get('username')?.value, this.form.get('password')?.value).subscribe(data => {
      console.log(data);
      this.router.navigateByUrl('home');
    }, (err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status == 401)
         {
          alert("401 - Not Found!");
         }
         else {
           alert(err.message);
         }
      }
    });
  }

  register() {
    console.log("register");
    this.router.navigateByUrl('signup');
  }

}
