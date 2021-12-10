import { User } from './../model/user.model';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UsernameValidators } from '../login/username.validators';
import { SignupService } from '../services/signup.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public user: User = {} as User;

  form = new FormGroup({
    userName: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      UsernameValidators.cannotContainSpace
    ]),
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  constructor(
    private router: Router,
    private signUpService: SignupService
  ) { }

  ngOnInit(): void {
  }

  public goBack() {
    this.router.navigateByUrl('login');
  }

  submitForm() {
    console.log(this.form.get("email")?.value);
    console.log(this.form.get("userName")?.value);
    console.log(this.form.get("password")?.value);
    this.user.email = this.form.get("email")?.value;
    this.user.userName = this.form.get("userName")?.value;
    this.user.password = this.form.get("password")?.value;
    console.log(this.form.value);
    console.log(this.user, this.user.appUserRole);

    this.signUpService.signUp(this.form.value).subscribe((userReceived: any) => {

      console.log(userReceived);

    });

  }

}
