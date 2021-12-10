import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { async, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private libraryBookPath: string = 'http://localhost:7777/library/api/v1/registration/login';
  private id: string = '5'

  constructor(private http: HttpClient) { }

  authenticate(username ?: string, password ?: string): Observable<HttpResponse<any>>{

    let headers = new HttpHeaders({
      authorization: 'Basic ' + btoa(username + ":" + password),

    });

    return this.http.get<any>(this.libraryBookPath, {
      headers: headers
    })
    .pipe(
      map((userData: any) => {
        console.log("USER LOGGED IN!!!!!!!! ", userData)
        sessionStorage.setItem('username', username!);

        console.log(sessionStorage.getItem('username'));

        let authString = 'Basic ' + btoa(username + ":" + password);
        sessionStorage.setItem('basicauth', authString);
        console.log(sessionStorage.getItem('basicauth'))

        return userData;
      })
    );
  }

  isUserLoggedIn() {
    return (!! sessionStorage.getItem('username') && !!sessionStorage.getItem('basicauth'))
  }

  logOut() {
    sessionStorage.clear();
  }
}
