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
            `
})
export class CoursesComponent {
    title = "List of Courses"
    
    getTitle() {
        return this.title;
    }

    courses: any;
    
    constructor(service: CoursesService) {
        this.courses = service.getCourses();
    }


    // Logic for calling an HTTP service
}