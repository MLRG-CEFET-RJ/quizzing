import {Quiz} from '../quiz/quiz.model';

export interface Activity
{
  id: number;
  quiz: Quiz;
  active: boolean;
  code: string;
}
