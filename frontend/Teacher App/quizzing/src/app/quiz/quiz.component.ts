import {Component, Inject, OnInit} from '@angular/core';
import {Quiz} from './quiz.model';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material';
import {DialogComponent} from '../commons/dialog/dialog.component';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {QuizService} from './quiz.service';
import {QuizDto} from '../_models/quizDTO.model';
import {Tag} from '../_models/tag.model';


@Component({
             selector:    'app-quiz',
             templateUrl: './quiz.component.html',
             styleUrls:   ['./quiz.component.css']
           })
export class QuizComponent implements OnInit
{

  quizzes: Quiz[];
  quiz: QuizDto;
  adding = false;

  constructor(public dialog: MatDialog,
              private quizService: QuizService)
  { }

  ngOnInit()
  {
    this.getUserQuizzes();
  }

  delete(q: Quiz)
  {
    const matDialogRef = this.openDialog();
    matDialogRef.afterClosed().subscribe(result => this.d(result, q));
  }

  d(result: boolean, q: Quiz)
  {
    if (result)
    {
      this.quizService.delete(q).subscribe(() => this.getUserQuizzes());
    }
  }

  openDialog(): MatDialogRef<DialogComponent, any>
  {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      title:     'Apagar Conteúdo',
      paragraph: 'Tem certeza que desea deletar a questão?',
      action:    true
    };
    dialogConfig.disableClose = true;

    return this.dialog.open(DialogComponent, dialogConfig);
  }

  createQuiz(): void
  {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '250px';
    dialogConfig.data = {
      name: null,
      tags: null
    };

    const dialogRef = this.dialog.open(CreateQuizComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result =>
                                      {
                                        this.quiz = new QuizDto();
                                        this.quiz.name = result.name;
                                        this.quiz.tags = result.tags;
                                        this.quiz.ids = [];
                                        this.adding = true;
                                      });
  }


  startActivity(quiz: Quiz)
  {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      title:     'Iniciar Atividade',
      paragraph: `Dando início a uma atividade com o questionário ${quiz.name}`,
      action:    true
    };
    dialogConfig.disableClose = true;

    return this.dialog.open(DialogComponent, dialogConfig);
  }

  private getUserQuizzes()
  {
    this.quizService.getQuizzes().subscribe(quizzes =>
                                            {
                                              this.quizzes = [];
                                              quizzes.forEach(quiz =>
                                                              {
                                                                const q = new Quiz();
                                                                q.name = quiz.name;
                                                                q.tags = quiz.tags;
                                                                q.questions = JSON.parse(quiz.questions);
                                                                this.quizzes.push(q);
                                                              });
                                            }
    );
  }
}

export interface DialogData
{
  name: string;
  tags: Tag[];
}

@Component({
             selector:    'create-quiz-component',
             templateUrl: 'create-quiz-component.html',
           })
export class CreateQuizComponent
{

  constructor(
    public dialogRef: MatDialogRef<CreateQuizComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData)
  {}

  onNoClick(): void
  {
    this.dialogRef.close();
  }

  addTags(t: Tag[])
  {
    this.data.tags = t;
  }
}
