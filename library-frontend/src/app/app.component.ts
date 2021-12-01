import { Component } from '@angular/core';
import { FavoriteChangedEventArgs } from './favorite/favorite.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  // title = 'library-frontend';
  // post = {
  //   title: "Title",
  //   isFavorite: true
  // }

  // onFavoriteChanged(eventsArgs: FavoriteChangedEventArgs) {
  //   console.log("Favorite changed", eventsArgs);
  // }
  courses = [1, 2];
  viewMode = 'map';

  coursesFor = [
    {id: 1, name: 'courses1'},
    {id: 2, name: 'courses2'},
    {id: 3, name: 'courses3'},
  ];

  onAdd() {
    this.coursesFor.push({id: 4, name: 'course4'});
  }

  onRemove(course?: any) {
    let index = this.coursesFor.indexOf(course);
    this.coursesFor.splice(index, 1);
  }
}

