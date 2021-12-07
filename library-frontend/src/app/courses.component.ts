import { Component } from "@angular/core";
import { CoursesService } from "./courses.service";

@Component({
    selector: 'courses',
    template: `
            <h2>{{"Title: " + getTitle() }}</h2>
            <ul>
                <li *ngFor="let course of courses">
                    {{ course }}
                </li>
            </ul>

            <table>
                <tr>
                    <td [attr.colspan]="colSpan"></td>
                </tr>
            </table>

            <button class="btn btn-primary" [class.active]="isActive"> Button </button>
            <button [style.backgroundColor]="isActive ? 'blue' : 'white' "> Save </button>
            
            <div (click)="onDivClicked()">
            <button (click)="onSave($event)">Save</button>
            </div>

            <input [value]="email" (keyup.enter)="onKeyUp();" />
            <input [(ngModel)]="email" (keyup.enter)="onKeyUp()" /> <br/>

            {{ course.title | uppercase | lowercase }} <br/>
            {{ course.students | number }} <br/>
            {{ course.rating | number:'1.1-1' }} <br/>
            {{ course.price | currency:'AUD':true:'3.2-2' }} <br/>
            {{ course.releaseDate | date:'shortDate' }} <br/>

            {{ text | summary:10 }}
            `
})
export class CoursesComponent {
    title = "List of Courses"
    colSpan = 2;
    isActive = true;
    email = "me@example.com";

    course = {
        title: "The Complete Angular Course",
        rating: 4.95734,
        students: 1231,
        price: 190.95,
        releaseDate: new Date(2021, 0, 12)
    }

    text = `
        Hello there, lorem ipsum is simply dummy
    `

    getTitle() {
        return this.title;
    }

    courses: any;
    
    constructor(service: CoursesService) {
        this.courses = service.getCourses();
    }

    onSave($event: any) {
        $event.stopPropagation();
        console.log("Button was clicked", $event);
    }

    onDivClicked() {
        console.log("Div was clicked");
    }

    onKeyUp() {
        // if ($event.keyCode === 13)
            // console.log("ENTER WAS PRESSED");
        // console.log($event.target.value);
        console.log(this.email);
    }
    // Logic for calling an HTTP service
}