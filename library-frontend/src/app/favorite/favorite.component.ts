import { Component, OnInit, Input, Output, EventEmitter, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css'],
  // encapsulation: ViewEncapsulation.ShadowDom
})
export class FavoriteComponent {
  @Input('isFavorite') isFavorite ?: boolean;
  @Output() change = new EventEmitter();

 
  onClick() {
    this.isFavorite = !this.isFavorite;
    this.change.emit({newValue : this.isFavorite});
  }

}

export interface FavoriteChangedEventArgs {
  newVale: boolean;
}