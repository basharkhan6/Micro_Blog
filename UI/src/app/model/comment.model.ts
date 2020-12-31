import {Post} from './post.model';
import {User} from './user.model';

export class Comment {

  id: number;
  text: string;
  commentedAt: Date;
  post: Post;
  commenter: User;

}
