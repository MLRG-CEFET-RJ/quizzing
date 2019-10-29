import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {API} from '../app.api';
import {QuizDto} from '../_models/quizDTO.model';

@Injectable({providedIn: 'root'})
export class QuizService
{
  constructor(private http: HttpClient) { }

  create(quiz: QuizDto)
  {
    // const header = new HttpHeaders();
    // header.set('Authorization', localStorage.getItem('Authorization'));
    // return this.http.post(`${API}/quiz/new`, quiz, {headers: header});
  }

  get()
  {

  }

  edit()
  {

  }

  delete()
  {

  }
}
