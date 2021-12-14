import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../model/book.model';
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class BookApiService {

  private libraryBookPath: string = 'http://localhost:7777/library';
  private id: string = '5'

  constructor(private http: HttpClient) { }

  public findBookById(): Observable<Book> {

    return this.http.get<any>(this.libraryBookPath + '/' + this.id);
  }
}
