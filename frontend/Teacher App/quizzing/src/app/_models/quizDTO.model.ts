import {Tag} from './tag.model';

export class QuizDto
{
  name: string;
  ids: Id[];
  tags: Tag[];
}

class Id
{
  id: string;
}
