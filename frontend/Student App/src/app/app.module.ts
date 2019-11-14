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
  MatRadioModule,
  MatSelectModule,
  MatSidenavModule,
  MatToolbarModule
} from '@angular/material';

import {KatexModule} from 'ng-katex';
import {DialogComponent} from './commons/dialog/dialog.component';
import {FormsModule} from '@angular/forms';
import {AlunoComponent} from './aluno/aluno.component';
import {LoginComponent} from './login/login.component';
import {HttpClientModule} from '@angular/common/http';


@NgModule({
            declarations:    [
              DialogComponent,
              AppComponent,
              AlunoComponent,
              LoginComponent
            ],
            entryComponents: [DialogComponent],
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
              MatRadioModule,
              MatCardModule,
              MatGridListModule,
              MatExpansionModule,
              MatCheckboxModule,
              MatSelectModule,
              MatChipsModule,
              KatexModule,
              MatDialogModule,
              MatInputModule,
              FormsModule
            ],
            exports:         [DialogComponent, AlunoComponent],
            providers:       [],
            bootstrap:       [AppComponent]
          })
export class AppModule {}
