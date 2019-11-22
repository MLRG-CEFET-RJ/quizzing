import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {API} from '../app.api';
import {Observable} from 'rxjs';
@Injectable({
              providedIn: 'root'
            })
export class ImportService
{

  constructor(private http: HttpClient) { }

  import(p: any): Observable<any>
  {
    return this.http.post(`${API}/upload/file`, p, {headers: this.getHeaders()});
  }

  getHeaders(): HttpHeaders
  {
    return new HttpHeaders({Authorization: sessionStorage.getItem('Authorization')});
  }
}
