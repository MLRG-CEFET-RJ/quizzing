import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {API} from '../app.api';
import {User} from '../user/user.model';

@Injectable({providedIn: 'root'})
export class AuthenticationService
{
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient)
  {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User
  {
    return this.currentUserSubject.value;
  }

  login(username, password)
  {
    return this.http.post<any>(`${API}/login`, {username, password}, {observe: 'response'})
    .pipe(
      map(
        response =>
        {
          const authorization = response.headers.get('Authorization');
          const user = atob(authorization.split('.')[1]);
          console.log(user);
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
          localStorage.setItem('Authorization', authorization);
          this.currentUserSubject.next(JSON.parse(user));
          return user;
        }
      )
    );
  }

  logout()
  {
    // remove user from local storage and set current user to null
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
