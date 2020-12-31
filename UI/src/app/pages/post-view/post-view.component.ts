import {Component, OnInit} from '@angular/core';
import {Post} from '../../model/post.model';
import {Comment} from '../../model/comment.model';
import {PostService} from '../../service/post.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '../../service/authentication.service';
import {VoteService} from '../../service/vote.service';
import {VoteType} from '../../model/enumeration/vote-type.enum';

@Component({
  selector: 'app-post-view',
  templateUrl: './post-view.component.html',
  styleUrls: ['./post-view.component.css']
})
export class PostViewComponent implements OnInit {

  postId: number;
  post: Post;
  comments: Comment[];
  loginNeeded: boolean;

  constructor(
    private route: ActivatedRoute,
    private auth: AuthenticationService,
    private router: Router,
    private postService: PostService,
    private voteService: VoteService) { }

  ngOnInit(): void {
    this.getPostId();
    this.getPost();
    this.loginNeeded = !this.auth.authenticated;
    this.getComments();
  }

  getPostId(): void {
    this.route.params.subscribe(params => {
      const paramName = 'id';
      this.postId = +params[paramName]; // (+) converts string 'id' to a number
      // console.log(this.postId);
      // this.router.navigate(["cngPass", this.id]);
      // window.sessionStorage.setItem('userId', String(this.id));
    });
  }

  getPost(): void {
    this.postService.getPost(this.postId).subscribe(
      post => {
        this.post = post;
        },
      error => {
        console.log(error);
      }
    );
  }

  getComments(): void {
    this.postService.getPostComments(this.postId).subscribe(
      comments => {
        this.comments = comments;
      },
      error => {
        console.log(error);
      }
    );
  }

  makeComment(text: string): void {
    this.postService.makeComment(text, this.postId).subscribe(
      comment => {
        this.comments.push(comment);
      },
      error => {
        console.log(error);
      }
    );
  }

  upVote(): void {
    this.voteService.changeVote(this.postId, VoteType.UPVOTE).subscribe(
      () => {
        this.getPost();
      },
      error => {
        console.log('Cannot change vote: Upvote');
        if (error.status === 401) {
          if (confirm('Login required\r\nWant to login?')) {
            this.redirectToLogin();
          }
        }
        if (error.status === 400) {
          alert('Already given.');
        }
      }
    );
  }

  downVote(): void {
    this.voteService.changeVote(this.postId, VoteType.DOWNVOTE).subscribe(
      () => {
        this.getPost();
      },
      error => {
        console.log('Cannot change vote: Downvote');
        if (error.status === 401) {
          if (confirm('Login required\r\nWant to login?')) {
            this.redirectToLogin();
          }
        }
        if (error.status === 400) {
          alert('Already given.');
        }
      }
    );
  }
  redirectToLogin(): void {
    this.router.navigate(['login'], { queryParams: { redirectUrl: this.router.url }});
  }

}
