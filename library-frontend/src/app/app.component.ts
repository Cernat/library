import { Component } from '@angular/core';
import { FavoriteChangedEventArgs } from './favorite/favorite.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'library-frontend';
  post = {
    title: "Title",
    isFavorite: true
  }

  onFavoriteChanged(eventsArgs: FavoriteChangedEventArgs) {
    console.log("Favorite changed", eventsArgs);
  }
}
