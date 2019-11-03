import {Component, Input, OnInit} from '@angular/core';
import {QuestionService} from '../question.service';
import {QuizDto} from '../../_models/quizDTO.model';
import {QuizService} from '../../quiz/quiz.service';
import {Router} from '@angular/router';
import {QuestionSolr} from '../../_models/questionSolr.model';

@Component({
             selector:    'app-search-question',
             templateUrl: './search-question.component.html'
           })
export class SearchQuestionComponent implements OnInit
{

  @Input() _quiz: QuizDto;
  searchResult: QuestionSolr[];

  constructor(private questionService: QuestionService,
              private quizService: QuizService,
              private router: Router)
  { }

  ngOnInit()
  {
  }

  @Input()
  set quiz(quiz: QuizDto)
  {
    this._quiz = quiz;
    const tags = quiz.tags.map(tag => tag.name).join(',');
    this.questionService.suggest(tags).subscribe(result => this.searchResult = result);
  }

  search()
  {
    const query = (document.getElementById('search') as HTMLInputElement).value;
    this.questionService.search(query).subscribe(result => this.searchResult = result);
  }

  add(id)
  {
    this._quiz.ids.push({id});
  }

  save()
  {
    this.quizService.create(this._quiz).subscribe(() => this.router.navigate(['/quiz']));
  }

  parse(options: string)
  {
    return JSON.parse(options);
  }
}
