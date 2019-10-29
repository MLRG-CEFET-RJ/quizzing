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

  create(question: Question)
  {
    return this.http.post(`${API}/question/new`, question, {headers: this.getHeaders()});
  }

  get(): Observable<any>
  {
    return this.http.get(`${API}/question/questions`, {headers: this.getHeaders()});
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
    const httpHeaders = new HttpHeaders({'Authorization': localStorage.getItem('Authorization').toString()});
    console.log(httpHeaders);
    return httpHeaders;
  }
}
