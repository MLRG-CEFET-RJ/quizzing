import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Activity} from './activity.model';
import {API} from '../app.api';
import {Quiz} from '../quiz/quiz.model';
import {Answers} from './results/answers.model';

@Injectable({providedIn: 'root'})
export class ActivityService
{
  constructor(private http: HttpClient) { }

  create(quiz: Quiz): Observable<any>
  {
    return this.http.post(`${API}/activity/new/${quiz.id}`, null, {headers: this.getHeaders()});
  }

  getActivities(): Observable<Activity[]>
  {
    return this.http.get<Activity[]>(`${API}/activity/activities`, {headers: this.getHeaders()});
  }

  delete(activity: Activity)
  {
    return this.http.delete(`${API}/activity/delete/${activity.id}`, {headers: this.getHeaders()});
  }

  stop(activity: Activity): Observable<any>
  {
    return this.http.post(`${API}/activity/stop/${activity.id}`, null, {headers: this.getHeaders()});
  }

  getResults(id: string): Observable<Answers[]>
  {
    return this.http.get<Answers[]>(`${API}/activity/${id}/results`, {headers: this.getHeaders()})
  }

  getHeaders(): HttpHeaders
  {
    return new HttpHeaders({Authorization: sessionStorage.getItem('Authorization')});
  }
}
