import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AppError } from './app-error';
import { BadInput } from './bad-inputs';
import { NotFoundError } from './not-found-error';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private url = 'https://jsonplaceholder.typicode.com/posts';

  constructor(private http: HttpClient) { }

  getPosts() {
    return this.http.get(this.url);

  }

  createPost(post ?: any) {
    return this.http.post(this.url, JSON.stringify(post))
    .pipe(
      catchError((error: Response) => {
        console.log(error);
        if (error.status === 400)
        return throwError(new BadInput(error.json()));

        return throwError(new AppError());
      })
    );
  }

  updatePost(post ?: any) {
    return this.http.patch(this.url + '/' + post.id, JSON.stringify({ isRead: true }));
  }

  deletePost(id ?: any) {
    return this.http.delete(this.url + '/' + id)
        .pipe(
            catchError((error: Response) => {
                console.log(error);                
                // return throwError(error);

                if(error.status === 404)
                    return throwError(new NotFoundError());
                else
                     return throwError(new AppError(error));
              })
         );
}



}
