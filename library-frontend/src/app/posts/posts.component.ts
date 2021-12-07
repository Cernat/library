import { NotFoundError } from './../services/not-found-error';
import { AppError } from './../services/app-error';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { PostService } from '../services/post.service';
import { BadInput } from '../services/bad-inputs';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit{

  posts ?: any;
 
  constructor(private service: PostService) {
  }

  ngOnInit() {
    this.service.getPosts()
      .subscribe(
      response => {
        this.posts = response as any[];
      }, 
      (error: Response) => {
        if (error.status === 404)
        alert('Not found.');
        else {
          alert('An unexpected error occurred.');
          console.log(error);
        }
        
      });
  }

  createPost(input: HTMLInputElement) {
    let post: any = { title: input.value };
    input.value = '';
    this.service.createPost(post)
      .subscribe(
      response  => {
        // post['id'] = response.json().id;
        this.posts.splice(0, 0, post);
        console.log(response);
      }, 
      (error: AppError) => {
        if (error instanceof BadInput)
        alert('An unexpected error occurred.');
        console.log(error);
      });
  }

  updatePost(post ?: any) {
    // this.http.put(this.url, JSON.stringify(post))
    this.service.updatePost(post)
    .subscribe(
    response => {
        console.log(response);
    }, 
    error => {
      alert('An unexpected error occurred.');
      console.log(error);
    });
  }

  deletePost(post ?: any) {
    this.service.deletePost(post.id)
      .subscribe(
      response => {
        let index = this.posts.indexOf(post.id);
        this.posts.splice(index, 1);
      }, 
      (error: AppError) => {
        if (error instanceof NotFoundError)
          alert('This post has already been deleted.');
        else {
          alert('An unexpected error occurred.');
          console.log(error);
        }
      });
  }


}