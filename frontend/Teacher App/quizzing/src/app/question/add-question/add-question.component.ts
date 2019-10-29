import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Tag} from '../../_models/tag.model';
import {Question} from '../question.model';
import {QuestionService} from '../question.service';

@Component({
             selector:    'app-add-question',
             templateUrl: './add-question.component.html',
             styleUrls:   ['../question.component.css']
           })
export class AddQuestionComponent implements OnInit
{

  form: FormGroup;
  public letters = [];
  tags: Tag[];

  constructor(private formBuilder: FormBuilder,
              private questionService: QuestionService) {}

  ngOnInit()
  {
    this.form = this.formBuilder.group(
      {
        question: ['', Validators.required],
        type:     ['', Validators.required],
        image:    [null],
        options:  new FormArray([])
      }
    );
    this.addOption();
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
          id:      [null, Validators.required],
          letter:  [null, Validators.required],
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
    this.questionService.create(q);
  }
}
