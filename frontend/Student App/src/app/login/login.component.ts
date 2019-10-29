import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	
	name:string;
	email:string;
	codigo:string;
	cansubmit:boolean = false;
  

	constructor(private router: Router) { }

	validateParameter() {
		if (this.name == null) {
			this.cansubmit = false;
		}
		else if (this.email == null) {
			this.cansubmit = false;
		}
		else if (this.codigo == null) {
			this.cansubmit = false;
		}
		else {
			this.cansubmit = true;
		}
	}

	ngOnInit() {
	}
	
	submit() {
		this.router.navigateByUrl('/aluno');
	}
	
}
