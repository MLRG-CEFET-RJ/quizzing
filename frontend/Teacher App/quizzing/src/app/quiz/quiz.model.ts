import {Question} from '../question/question.model';
import {Tag} from '../_models/tag.model';

export class Quiz
{
  id: number;
  name: string;
  questions: Question[];
  tags: Tag[];
}
