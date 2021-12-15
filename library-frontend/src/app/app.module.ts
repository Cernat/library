import { AuthGuard } from './auth.guard';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AuthenticationService } from './services/authentication.service';
import { BookApiService } from './services/book-api.service';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CdsModule } from '@cds/angular';
import { ClarityModule } from '@clr/angular';
import { AppRoutingModule } from './app-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { SignupService } from './services/signup.service';
import { HomeService } from './services/home.service';
import { AuthInterceptor } from './auth.interceptor';
 
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    SignupComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CdsModule,
    ClarityModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [
    AuthenticationService,
    BookApiService,
    SignupService,
    AuthGuard,
    HomeService, {
     provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true 
    }
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
