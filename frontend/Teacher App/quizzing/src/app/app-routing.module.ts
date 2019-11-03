import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {QuestionComponent} from './question/question.component';
import {QuizComponent} from './quiz/quiz.component';
import {ActivityComponent} from './activity/activity.component';
import {AddQuestionComponent} from './question/add-question/add-question.component';
import {EditQuestionComponent} from './question/edit-question/edit-question.component';
import {AddQuizComponent} from './quiz/add-quiz/add-quiz.component';
import {ImportComponent} from './import/import.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {AuthGuard} from './_helpers/oauth.guard';
import {AppComponent} from './app.component';


const routes: Routes = [
  {path: '', component: AppComponent, canActivate: [AuthGuard]},

  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},

  {path: 'question', component: QuestionComponent, canActivate: [AuthGuard]},
  {path: 'quiz', component: QuizComponent, canActivate: [AuthGuard]},
  {path: 'activity', component: ActivityComponent, canActivate: [AuthGuard]},
  {path: 'question/add', component: AddQuestionComponent, canActivate: [AuthGuard]},
  {path: 'question/edit/:id', component: EditQuestionComponent, canActivate: [AuthGuard]},
  {path: 'quiz/add', component: AddQuizComponent, canActivate: [AuthGuard]},
  {path: 'import', component: ImportComponent, canActivate: [AuthGuard]},

  {path: '**', redirectTo: '', canActivate: [AuthGuard]}
];

@NgModule({
            imports: [RouterModule.forRoot(routes)],
            exports: [RouterModule]
          })
export class AppRoutingModule {}
