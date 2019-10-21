import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {QuestionComponent} from './question/question.component';
import {QuizComponent} from './quiz/quiz.component';
import {ActivityComponent} from './activity/activity.component';
import {AddQuestionComponent} from './question/add-question/add-question.component';
import {EditQuestionComponent} from './question/edit-question/edit-question.component';
import {TextEditorComponent} from './commons/text-editor/text-editor.component';
import {AddQuizComponent} from './quiz/add-quiz/add-quiz.component';
import {EditQuizComponent} from './quiz/edit-quiz/edit-quiz.component';
import {SearchQuestionComponent} from './question/search-question/search-question.component';
import { ImportComponent } from './import/import.component';




const routes: Routes = [
  {path: 'question', component: QuestionComponent},
  {path: 'quiz', component: QuizComponent},
  {path: 'activity', component: ActivityComponent},
  {path: 'question/add', component: AddQuestionComponent},
  {path: 'question/search', component: SearchQuestionComponent},
  {path: 'question/edit/:id', component: EditQuestionComponent},
  {path: 'quiz/add', component: AddQuizComponent},
  {path: 'quiz/edit/:id', component: EditQuizComponent},
  {path: 'test', component: TextEditorComponent},
  {path: 'import', component: ImportComponent},

  {path: '**', redirectTo: '/'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
