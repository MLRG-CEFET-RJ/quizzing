import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API} from './app.api';

@Injectable({
              providedIn: 'root'
            })
export class AppService
{

  constructor(private http: HttpClient) { }

  joinActivity(code: string)
  {
    return this.http.post(`${API}/rest/student/activity/${code}`, null);
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
