import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {API} from '../app.api';
import {Question} from './question.model';
import {Rate} from '../_models/rate.model';
import {Observable} from 'rxjs';

@Injectable({
              providedIn: 'root'
            })
export class QuestionService
{

  constructor(private http: HttpClient) { }

  create(question: Question): Observable<any>
  {
    return this.http.post(`${API}/question/new`, question, {headers: this.getHeaders()});
  }

  getQuestions(): Observable<Question[]>
  {
    return this.http.get<Question[]>(`${API}/question/questions`, {headers: this.getHeaders()});
  }

  getQuestion(id: string): Observable<Question>
  {
    return this.http.get<Question>(`${API}/question/questions/${id}`, {headers: this.getHeaders()});
  }

  edit(question: Question)
  {
    return this.http.put(`${API}/question/edit`, question, {headers: this.getHeaders()});
  }

  delete(question: Question)
  {
    return this.http.delete(`${API}/question/delete/${question.id}`, {headers: this.getHeaders()});
  }

  search(q: string)
  {
    return this.http.get(`${API}/search/questions?q=${q}`, {headers: this.getHeaders()});
  }

  rate(rate: Rate)
  {
    return this.http.post(`${API}/question/rate`, rate, {headers: this.getHeaders()});
  }

  getHeaders(): HttpHeaders
  {
    return new HttpHeaders({Authorization: sessionStorage.getItem('Authorization')});
  }
}
