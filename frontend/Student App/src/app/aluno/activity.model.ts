import {Quiz} from './quiz.model';

export interface Activity
{
  id: number;
  quiz: Quiz;
  ended: boolean;
  code: string;
}
