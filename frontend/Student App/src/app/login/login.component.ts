import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
             selector:    'app-login',
             templateUrl: './login.component.html',
             styleUrls:   ['./login.component.css']
           })
export class LoginComponent implements OnInit
{

  name: string;
  codigo: string;
  cansubmit = false;


  constructor(private router: Router) { }

  validateParameter()
  {
    if (this.name == null)
    {
      this.cansubmit = false;
    }
    else
    {
      this.cansubmit = this.codigo != null;
    }
  }

  ngOnInit()
  {
  }

  submit()
  {
    sessionStorage.setItem('name', this.name);
    this.router.navigate([`/aluno/${this.codigo}`]);
  }

}
