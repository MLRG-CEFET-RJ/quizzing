import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {API} from '../app.api';
import {Observable} from 'rxjs';
import {Quiz} from './quiz.model';
import {QuizModel} from '../_models/quiz.model';
import {QuizDto} from '../_models/quizDTO.model';

@Injectable({providedIn: 'root'})
export class QuizService
{
  constructor(private http: HttpClient) { }

  create(quiz: QuizDto): Observable<any>
  {
    return this.http.post(`${API}/quiz/new`, quiz, {headers: this.getHeaders()});
  }

  getQuizzes(): Observable<QuizModel[]>
  {
    return this.http.get<QuizModel[]>(`${API}/quiz/quizzes`, {headers: this.getHeaders()});
  }

  getQuiz(id: string): Observable<Quiz>
  {
    return this.http.get<Quiz>(`${API}/quiz/questions/${id}`, {headers: this.getHeaders()});
  }

  edit(quiz: Quiz)
  {
    return this.http.put(`${API}/quiz/edit`, quiz, {headers: this.getHeaders()});
  }

  delete(quiz: Quiz)
  {
    return this.http.delete(`${API}/quiz/delete/${quiz.id}`, {headers: this.getHeaders()});
  }

  getHeaders(): HttpHeaders
  {
    return new HttpHeaders({Authorization: sessionStorage.getItem('Authorization')});
  }
}
