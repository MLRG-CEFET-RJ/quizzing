import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {API} from '../app.api';
import {User} from './user.model';

@Injectable({providedIn: 'root'})
export class UserService
{
  constructor(private http: HttpClient) { }

  register(user: User)
  {
    return this.http.post(`${API}/users/sign-up`, user);
  }

  delete(user: User)
  {
    // @ts-ignore
    return this.http.delete(`${API}/users/delete`, user);
  }
}
