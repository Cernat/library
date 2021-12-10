import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { HomeService } from '../services/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private homeService: HomeService,
    private auth: AuthenticationService
  ) { }

  ngOnInit(): void {
    this.retrieveAllUsers();
  }

  retrieveAllUsers() {

    this.homeService.receiveAllUser().subscribe(allUsers => {
      console.log(allUsers);
    });
  }
  
}
