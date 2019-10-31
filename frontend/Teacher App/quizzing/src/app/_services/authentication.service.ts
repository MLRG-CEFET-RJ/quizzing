import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
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
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(sessionStorage.getItem('currentUser')));
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

                     // store user details and jwt token in local storage to keep user logged in between page refreshes
                     sessionStorage.setItem('currentUser', JSON.stringify(user));
                     sessionStorage.setItem('Authorization', authorization);
                     this.currentUserSubject.next(JSON.parse(user));
                     return user;
                   }
                 )
               );
  }

  logout()
  {
    // remove user from local storage and set current user to null
    sessionStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
