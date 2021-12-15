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
    password: new FormControl('', Validators.required),
    userRole: new FormControl('', Validators.required)
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
    console.log(this.form.value);
    this.signUpService.signUp(this.form.value).subscribe((userReceived: any) => {
      console.log(userReceived);
    });
  }

}
