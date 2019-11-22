import {Activity} from '../activity.model';

export interface Answers
{
  id: number;
  student: string;
  activity: Activity;
  answer: string
}
