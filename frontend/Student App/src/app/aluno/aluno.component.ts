import {Component, OnInit} from '@angular/core';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material';
import {DialogComponent} from '../commons/dialog/dialog.component';
import {ActivatedRoute, Router} from '@angular/router';
import {AppService} from '../app.service';
import {log} from 'util';

@Component({
             selector:    'app-aluno',
             templateUrl: './aluno.component.html',
             styleUrls:   ['./aluno.component.css']
           })
export class AlunoComponent implements OnInit
{
  activity: any = null;
  questions = [];
  answers: any = [];

  constructor(public dialog: MatDialog,
              private router: Router,
              private appService: AppService,
              private route: ActivatedRoute) { }

  ngOnInit()
  {
    this.appService.joinActivity(this.route.snapshot.params.code).subscribe(activity =>
                                                                            {
                                                                              this.activity = activity;
                                                                              activity.quiz.questions.forEach(
                                                                                q =>
                                                                                {
                                                                                  q.options = JSON.parse(q.options);
                                                                                  this.questions.push(q);
                                                                                }
                                                                              )
                                                                            });
  }

  submit()
  {
    console.log(this.answers);
    const matDialogRef = this.openDialog();
    matDialogRef.afterClosed().subscribe(result => {
      if(result)
      {
        this.d();
      }
    });
  }

  d()
  {
    this.appService.submitAnswers(this.activity.id, {answers:this.answers}).subscribe(
      result => log(result)
    );
  }

  openDialog(): MatDialogRef<DialogComponent, any>
  {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      title:     'Enviar Atividade',
      paragraph: 'Tem certeza que deseja enviar a atividade?',
      action:    true
    };
    dialogConfig.disableClose = true;
    return this.dialog.open(DialogComponent, dialogConfig);
  }

  answerQuestion(idQuestion, idOption)
  {
    this.answers.push({
      question: idQuestion,
      answer: idOption
    });
  }

  getOptions(options): any[]
  {
    return JSON.parse(options).sort((a, b) => (a.letter > b.letter) ? 1 : -1);
  }
}
