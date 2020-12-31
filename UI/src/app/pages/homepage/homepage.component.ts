import { Component, OnInit } from '@angular/core';
import {PageEvent} from '@angular/material/paginator';
import {PostService} from '../../service/post.service';
import {Post} from '../../model/post.model';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  // MatPaginator Inputs
  length = 100;
  pageSize = 10;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  // MatPaginator Output
  pageEvent: PageEvent;

  posts: Post[];

  constructor(private postService: PostService) { }

  ngOnInit(): void {
    this.getAllPost();
  }

  getAllPost(): void {
    this.postService.getAllPost().subscribe(
      posts => {
        this.posts = posts;
      },
      error => {
        console.log(error);
      }
    );
  }

}
