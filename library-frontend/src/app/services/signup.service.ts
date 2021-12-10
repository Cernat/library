import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  private libraryUserPath: string = 'http://localhost:7777/library/api/v1/registration';
  private id: string = '5'

  constructor(private http: HttpClient) { }

  signUp(userToSend: User):  Observable<User> {

    return this.http.post<User>(this.libraryUserPath, userToSend);
  }


}
