import { Component, OnInit } from '@angular/core';
import {PostService} from '../../service/post.service';
import {Post} from '../../model/post.model';
import {AuthenticationService} from '../../service/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  post = new Post();

  constructor(
    private auth: AuthenticationService,
    private postService: PostService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (!this.auth.authenticated) {
      this.router.navigate(['login'], { queryParams: { redirectUrl: this.router.url }});
    }
  }

  savePost(): void {
    this.postService.savePost(this.post).subscribe(
      post => {
        console.log('Post Save Success');
        this.router.navigateByUrl('/posts/' + post.id);
      },
      error => {
        console.log('Error in saving post');
        console.log(error);
      }
    );
  }
}
