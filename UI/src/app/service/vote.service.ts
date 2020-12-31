import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {VoteType} from '../model/enumeration/vote-type.enum';

@Injectable({
  providedIn: 'root'
})
export class VoteService {

  baseUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) {
  }

  // countVotes(postId: number, type: VoteType): Observable<any> {
  //   return this.http.get<number>(this.baseUrl + 'posts/' + postId + '/votes/' + type);
  // }

  changeVote(postId: number, type: VoteType): Observable<any> {
    return this.http.post(this.baseUrl + 'posts/' + postId + '/votes',
                          JSON.stringify(type.toString()),
      {headers: new HttpHeaders().set('Content-Type', 'application/json')});
  }
}
