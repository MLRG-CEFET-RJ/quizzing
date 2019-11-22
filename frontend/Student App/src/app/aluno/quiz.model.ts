import {Tag} from './tag.model';
import {Question} from './question.model';

export class Quiz
{
  id: number;
  name: string;
  questions: Question[];
  tags: Tag[];
}
