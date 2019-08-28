import { Component, OnInit } from '@angular/core';
import {Quiz} from './quiz.model';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit {

  quizzes: Quiz[];

  constructor() { }

  ngOnInit() {
  }

  delete(index: number)
  {
    if (confirm(`quer deletar ${index}?`))
    {
      alert('Deletado');
    }
  }
}
