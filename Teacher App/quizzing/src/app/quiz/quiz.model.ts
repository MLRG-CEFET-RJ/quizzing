import {Question} from '../question/question.model';

export interface Quiz
{
  id: number;
  questions: Question[];
}
