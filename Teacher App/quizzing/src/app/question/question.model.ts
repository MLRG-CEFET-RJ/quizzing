import {Option} from '../_models/option.model';
import {Tag} from '../_models/tag.model';

export class Question
{
  id: number;
  question: string;
  options: Option[];
  type: string;
  rating: number;
  image: string;
  tags: Tag[];
}
