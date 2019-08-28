import {Component, OnInit} from '@angular/core';
import {Question} from './question.model';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material';
import {DialogComponent} from '../commons/dialog/dialog.component';

@Component({
             selector:    'app-question',
             templateUrl: './question.component.html',
             styleUrls:   ['./question.component.css']
           })
export class QuestionComponent implements OnInit
{

  questions: Question[] = [
    {
      id:       1,
      question: 'Dadas as retas 𝑟 ≡ 3𝑥 + 𝑦 − 1 = 0 e 𝑠 ≡ 2𝑥 + 𝑚𝑦 − 8 = 0, qual dos seguintes é um\n' +
                'valor de m que faz com que as retas r e s formem um ângulo de 45°',
      type:     'TRUE_OR_FALSE',
      image:    'data:image/png;base64,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',
      options:  [],
      rating:   5,
      tags:     null
    },
    {
      id:       2,
      question: 'Dadas as retas 𝑟 ≡ 3𝑥 + 𝑦 − 1 = 0 e 𝑠 ≡ 2𝑥 + 𝑚𝑦 − 8 = 0, qual dos seguintes é um\n' +
                'valor de m que faz com que as retas r e s formem um ângulo de 45°',
      type:     'TRUE_OR_FALSE',
      image:    null,
      options:  [],
      rating:   2,
      tags:     null
    }
  ];

  constructor(public dialog: MatDialog) { }

  getStars(rating: number)
  {
    const stars = new Array(5);
    stars.fill(1, 0, rating);
    return stars.fill(0, rating);
  }

  ngOnInit() {}

  delete(q: Question)
  {
    let matDialogRef = this.openDialog();

    matDialogRef.afterClosed().subscribe(result => this.d(result, q));
  }

  d(result: boolean, q: Question)
  {
    if (result)
    {
      alert(`${q.id} deletada!`);
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
}
