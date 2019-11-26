import {Component, Input, OnInit} from '@angular/core';

@Component({
             selector:    'app-results',
             templateUrl: './results.component.html',
             styleUrls:   ['./results.component.css']
           })
export class ResultsComponent implements OnInit
{

  @Input() results;
  displayedColumns: string[] = [];
  dataSource: any[] = [];
  questions: any[];
  answer: any;

  constructor() { }

  ngOnInit()
  {
    this.questions = JSON.parse(this.results.questions);
    this.questions.forEach((e, i) => this.displayedColumns.push(`Q${i}`));
    this.displayedColumns.push('total');
    this.answer = JSON.parse(this.results.answers.answer);

    let o = {
      ans:   [],
      total: 0
    };
    o.total = this.getTotal(this.answer.answers);
    this.answer.answers.forEach(e2 => {
      e2.letter = this.getLetter(e2.question, e2.answer);
      o.ans.push(e2);
    });
    this.dataSource.push(o);
  }

  getTotal(e)
  {
    let size = e.length;
    let right = 0;
    {
      e.forEach(e2 =>
                {
                  let question = this.questions.find(q => q.id == e2.question);
                  if (question.answer == e2.answer)
                  {
                    right++;
                  }
                });
    }

    return Math.round((right * 100 / size * 10)) / 10;
  }

  getLetter(questionId, answerId)
  {
    const question = this.questions.find(q => q.id == questionId);
    const option = JSON.parse(question.options).find(o => o.id == answerId);
    return option.letter;
  }

  getClass(e)
  {
    const question = this.questions.find(q => q.id == e.question);
    if (question.answer == e.answer)
    {
      return 'correct';
    }
    else
    {
      return 'wrong';
    }
  }
}
