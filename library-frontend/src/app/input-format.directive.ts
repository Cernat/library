import { Directive, ElementRef, HostListener, Input, ViewEncapsulation } from '@angular/core';

@Directive({
  selector: '[appInputFormat]'
})
export class InputFormatDirective {
  @Input('appInputFormat') format: any;

  @HostListener('focus') onFocus() {
    console.log("on Focus");
  }

  @HostListener('blur') onBlur() {
    // console.log("on blur");
    let value = this.el.nativeElement.value;

    if (this.format == 'lowercase')
      this.el.nativeElement.value = value.toLowerCase();
    else
      this.el.nativeElement.value = value.toUpperCase();
  } 
  constructor(private el: ElementRef) { }

}
