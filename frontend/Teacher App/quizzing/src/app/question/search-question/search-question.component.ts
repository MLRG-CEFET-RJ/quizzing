import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {QuestionService} from '../question.service';
import {QuizDto} from '../../_models/quizDTO.model';
import {QuizService} from '../../quiz/quiz.service';
import {Router} from '@angular/router';
import {QuestionSolr} from '../../_models/questionSolr.model';
import {Rate} from '../../_models/rate.model';

@Component({
             selector:    'app-search-question',
             templateUrl: './search-question.component.html'
           })
export class SearchQuestionComponent implements OnInit
{

  // tslint:disable-next-line:variable-name
  @Input() _quiz: QuizDto;
  @Output() saved = new EventEmitter<boolean>();
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
    if (quiz.tags)
    {
      const tags = quiz.tags.map(tag => tag.name).join(',');
      this.questionService.suggest(tags).subscribe(result => this.searchResult = result);
    }
  }

  search()
  {
    const query = (document.getElementById('search') as HTMLInputElement).value;
    this.questionService.search(query).subscribe(result => this.searchResult = result);
  }

  add(id)
  {
    this._quiz.ids.push({id});
    document.getElementById('add_question_' + id).style.display = 'none';
    document.getElementById('remove_question_' + id).style.display = 'block';
  }

  remove(id)
  {
    const removeIndex = this._quiz.ids.map(item => item.id).indexOf(id);
    this._quiz.ids.splice(removeIndex, 1);
    document.getElementById('add_question_' + id).style.display = 'block';
    document.getElementById('remove_question_' + id).style.display = 'none';
  }

  save()
  {
    this.quizService.create(this._quiz).subscribe(() => this.saved.emit(true));
  }

  parse(options: string)
  {
    return JSON.parse(options);
  }

  getStars(rating: number)
  {
    const stars = new Array(5);
    stars.fill(1, 0, rating);
    return stars.fill(0, rating);
  }

  rate(id, value)
  {
    const rates = ['ONE', 'TWO', 'THREE', 'FOUR', 'FIVE'];
    const userRate = new Rate();
    userRate.questionId = id;
    userRate.rating = rates[value];
    this.questionService.rate(userRate).subscribe(
      () => alert('Questão avaliada!')
    );
  }
}
