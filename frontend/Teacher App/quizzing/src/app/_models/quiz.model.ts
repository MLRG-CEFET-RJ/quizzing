import {Tag} from './tag.model';

export class QuizModel
{
  id: number;
  name: string;
  questions: string;
  tags: Tag[];
}
