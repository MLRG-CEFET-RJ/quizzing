import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Question} from '../question.model';
import {Option} from '../../_models/option.model';
import {Tag} from '../../_models/tag.model';

@Component({
             selector:    'app-edit-question',
             templateUrl: './edit-question.component.html',
             styleUrls:   ['../question.component.css']
           })
export class EditQuestionComponent implements OnInit
{

  form: FormGroup;
  public letters = [];
  tags: Tag[];
  question: Question = {
    id:       1,
    // tslint:disable-next-line:max-line-length
    question: 'Dadas as retas 𝑟 ≡ 3𝑥 + 𝑦 − 1 = 0 e 𝑠 ≡ 2𝑥 + 𝑚𝑦 − 8 = 0, qual dos seguintes é um valor de m que faz com que as retas r e s formem um ângulo de 45°',
    type:     'TRUE_OR_FALSE',
    image:    null,
    options:  [
      {
        id:      '1',
        option:  'verdadeiro',
        letter:  'A',
        checked: false
      },
      {
        id:      '2',
        option:  'falso',
        letter:  'B',
        checked: true
      }
    ],
    rating:   5,
    tags:     [
      {
        tag: "a"
      },
      {
        tag: "b"
      }
    ]
  };

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit()
  {
    this.form = this.formBuilder.group(
      {
        question: [this.question.question, Validators.required],
        type:     [this.question.type, Validators.required],
        image:    [null],
        options:  new FormArray([])
      }
    );
    this.question.options.sort((a, b) => a.letter.charCodeAt(0) - b.letter.charCodeAt(0)).forEach(value => this.getOption(value));
  }

  get f() { return this.form.controls; }

  get t() { return this.f.options as FormArray; }

  addOption()
  {
    this.letters.push(String.fromCharCode(65 + this.letters.length));
    for (let i = this.t.length; i < this.letters.length; i++)
    {
      this.t.push(this.formBuilder.group(
        {
          option:  ['', Validators.required],
          id:      ['', Validators.required],
          letter:  ['', Validators.required],
          checked: [false, Validators.required]
        })
      );
    }
  }

  removeOption()
  {
    this.letters.pop();
    for (let i = this.t.length; i > this.letters.length; i--)
    {
      this.t.removeAt(i - 1);
    }
  }

  getOption(option: Option)
  {
    this.letters.push(String.fromCharCode(65 + this.letters.length));
    for (let i = this.t.length; i < this.letters.length; i++)
    {
      this.t.push(this.formBuilder.group(
        {
          option:  [option.option, Validators.required],
          id:      [option.id, Validators.required],
          letter:  [option.letter, Validators.required],
          checked: [option.checked, Validators.required]
        })
      );
    }
  }

  addTags(t: Tag[])
  {
    this.tags = t;
  }

  submit()
  {
    const form = this.form.value;

    const q: Question = new Question();
    q.tags = this.tags;
    q.type = form.type;
    q.image = form.image;
    q.question = form.question;
    q.options = form.options;
    console.log(q);
  }
}
