import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Post} from '../model/post.model';

@Injectable({
  providedIn: 'root'
})

export class PostService {

  baseUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) {
  }

  getPost(id: number): Observable<Post> {
    return this.http.get<Post>(this.baseUrl + 'posts/' + id);
  }

  getAllPost(): Observable<Post[]> {
    return this.http.get<Post[]>(this.baseUrl + 'posts');
  }

  savePost(post: Post): Observable<any> {
    return this.http.post<Post>(this.baseUrl + 'posts', post);
  }

  getPostComments(postId: number): Observable<any> {
    return this.http.get<Comment[]>(this.baseUrl + 'posts/' + postId + '/comments');
  }

  makeComment(text: string, postId: number): Observable<any> {
    return this.http.post<Comment>(this.baseUrl + 'posts/' + postId + '/comments', { text: text});
  }

}
