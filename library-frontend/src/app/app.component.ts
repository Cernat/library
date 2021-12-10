import { Component } from '@angular/core';
import { BookApiService } from './services/book-api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'library-frontend';

  constructor(
    private bookApi: BookApiService
  ) {}

  getBook() {
    this.bookApi.findBookById().subscribe(data => {
      console.log(data);
    });
  }
}
