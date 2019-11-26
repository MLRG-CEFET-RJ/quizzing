import {Component, OnInit} from '@angular/core';
import {ActivityService} from '../activity.service';
import {ActivatedRoute} from '@angular/router';

@Component({
             selector:    'app-results',
             templateUrl: './results.component.html',
             styleUrls:   ['./results.component.css']
           })
export class ResultsComponent implements OnInit
{

  id: string;
  displayedColumns: string[] = [];
  geral: any;
  dataSource: any[] = [];

  constructor(private activityService: ActivityService,
              private route: ActivatedRoute,) { }

  ngOnInit()
  {
    this.id = this.route.snapshot.params.id;
    this.activityService.getResults(this.id).subscribe(r =>
                                                             {
                                                               this.geral = r;
                                                               this.displayedColumns.push('name');
                                                               JSON.parse(this.geral[0].activity.quiz.questions).forEach((e, i) => this.displayedColumns.push(`Q${i}`));
                                                               this.displayedColumns.push('total');
                                                               this.geral.forEach(e =>
                                                                                  {
                                                                                    let o = {
                                                                                      name:  undefined,
                                                                                      ans:   [],
                                                                                      total: 0
                                                                                    };
                                                                                    o.name = e.student;

                                                                                    const e1 = JSON.parse(e.answer);
                                                                                    o.total = this.getTotal(e1.answers);
                                                                                    e1.answers.forEach(e2 => {
                                                                                      e2.letter = this.getLetter(e2.question, e2.answer);
                                                                                      o.ans.push(e2);
                                                                                    });
                                                                                    this.dataSource.push(o);
                                                                                  });
                                                             });
  }

  getClass(e)
  {
    const question = JSON.parse(this.geral[0].activity.quiz.questions).find(q => q.id == e.question);
    if (question.answer == e.answer)
    {
      return 'correct';
    }
    else
    {
      return 'wrong';
    }
  }

  getTotal(e)
  {
    let size = e.length;
    let right = 0;
    {
      e.forEach(e2 =>
                {
                  let question = JSON.parse(this.geral[0].activity.quiz.questions).find(q => q.id == e2.question);
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
    const question = JSON.parse(this.geral[0].activity.quiz.questions).find(q => q.id == questionId);
    const option = JSON.parse(question.options).find(o => o.id == answerId);
    return option.letter;
  }

}
