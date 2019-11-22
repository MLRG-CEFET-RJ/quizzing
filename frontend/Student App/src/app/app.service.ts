import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API} from './app.api';
import {Observable} from 'rxjs';
import {Activity} from './aluno/activity.model';

@Injectable({
              providedIn: 'root'
            })
export class AppService
{

  constructor(private http: HttpClient) { }

  joinActivity(code: string): Observable<Activity>
  {
    return this.http.post<Activity>(`${API}/rest/student/activity/${code}`, null);
  }

  submitAnswers(id: number, answer: any)
  {
    return this.http.post(`${API}/rest/student/${this.getName()}/activity/${id}/answers`, answer);
  }

  getName()
  {
    return sessionStorage.getItem('name');
  }
}
