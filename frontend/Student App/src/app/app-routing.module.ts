import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AlunoComponent } from './aluno/aluno.component';
import { LoginComponent } from './login/login.component';


const routes: Routes = [
  {path: 'aluno/:code', component: AlunoComponent},
  {path: 'login', component: LoginComponent},

  {path: '**', redirectTo: '/'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
