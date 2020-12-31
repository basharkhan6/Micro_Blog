import {User} from './user.model';

export class Post {

  id: number;
  title: string;
  description: string;
  createdAt: Date;
  upVote: number;
  downVote: number;
  author: User;

}
