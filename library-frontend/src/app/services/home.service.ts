import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  private libraryBookPath: string = 'http://localhost:7777/library/api/v1/user';
  private id: string = '5'

  constructor(
    private http: HttpClient
  ) { }

  receiveAllUser() {

    // let headers = new HttpHeaders({
    //   authorization: sessionStorage.getItem('basicauth')!

    // });

    return this.http.get<any>(this.libraryBookPath + '/', {
    });

  }

}
