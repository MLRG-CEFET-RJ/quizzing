import {Component, OnInit} from '@angular/core';
import {Question} from './question.model';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material';
import {DialogComponent} from '../commons/dialog/dialog.component';
import {QuestionService} from './question.service';
import {Router} from '@angular/router';

@Component({
             selector:    'app-question',
             templateUrl: './question.component.html',
             styleUrls:   ['./question.component.css']
           })
export class QuestionComponent implements OnInit
{

  questions: Question[];

  constructor(public dialog: MatDialog,
              private questionService: QuestionService,
              private router: Router)
  { }

  getStars(rating: number)
  {
    const stars = new Array(5);
    stars.fill(1, 0, rating);
    return stars.fill(0, rating);
  }

  ngOnInit()
  {
    this.getUserQuestions();
  }

  private getUserQuestions()
  {
    this.questionService.getQuestions().subscribe(questions =>
                                                  {
                                                    this.questions = questions;
                                                  });
  }

  delete(q: Question)
  {
    const matDialogRef = this.openDialog();
    matDialogRef.afterClosed().subscribe(result => this.d(result, q));
  }

  d(result: boolean, q: Question)
  {
    if (result)
    {
      this.questionService.delete(q).subscribe(() => this.getUserQuestions());
    }
  }

  openDialog(): MatDialogRef<DialogComponent, any>
  {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      title:     'Apagar Conteúdo',
      paragraph: 'Tem certeza que deseja deletar a questão?',
      action:    true
    };
    dialogConfig.disableClose = true;

    return this.dialog.open(DialogComponent, dialogConfig);
  }

}
