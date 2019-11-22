import {Tag} from './tag.model';

export class Question
{
  id: number;
  question: string;
  options: string;
  type: string;
  rating: number;
  image: string;
  tags: Tag[];
}
