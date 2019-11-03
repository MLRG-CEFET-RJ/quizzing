import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDialogModule,
  MatExpansionModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatSelectModule,
  MatSidenavModule,
  MatToolbarModule
} from '@angular/material';
import {QuestionComponent} from './question/question.component';
import {QuizComponent, CreateQuizComponent} from './quiz/quiz.component';
import {ActivityComponent} from './activity/activity.component';
import {AddQuestionComponent} from './question/add-question/add-question.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {EditQuestionComponent} from './question/edit-question/edit-question.component';
import {ChipComponent} from './commons/chip/chip.component';
import {TextEditorComponent} from './commons/text-editor/text-editor.component';
import {KatexModule} from 'ng-katex';
import {DialogComponent} from './commons/dialog/dialog.component';
import { AddQuizComponent } from './quiz/add-quiz/add-quiz.component';
import { EditQuizComponent } from './quiz/edit-quiz/edit-quiz.component';
import { ImportComponent } from './import/import.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {JwtInterceptor} from './_helpers/jwt.interceptor';
import {ErrorInterceptor} from './_helpers/error.interceptor';
import {SearchQuestionComponent} from './question/search-question/search-question.component';

@NgModule({
            declarations:    [
              AppComponent,
              LoginComponent,
              RegisterComponent,
              QuestionComponent,
              QuizComponent,
              ActivityComponent,
              AddQuestionComponent,
              EditQuestionComponent,
              SearchQuestionComponent,
              ChipComponent,
              TextEditorComponent,
              DialogComponent,
              AddQuizComponent,
              EditQuizComponent,
              CreateQuizComponent,
              ImportComponent
            ],
            entryComponents: [DialogComponent, CreateQuizComponent],
            imports:         [
              HttpClientModule,
              BrowserModule,
              AppRoutingModule,
              BrowserAnimationsModule,
              MatToolbarModule,
              MatSidenavModule,
              MatListModule,
              MatButtonModule,
              MatIconModule,
              MatCardModule,
              MatGridListModule,
              MatExpansionModule,
              ReactiveFormsModule,
              MatCheckboxModule,
              MatSelectModule,
              MatChipsModule,
              KatexModule,
              FormsModule,
              MatDialogModule,
              MatInputModule,
            ],
            exports:         [DialogComponent, CreateQuizComponent],
            providers:       [
              {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
              {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
            ],
            bootstrap:       [AppComponent]
          })
export class AppModule {}
