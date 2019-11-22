import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Tag} from '../../_models/tag.model';
import {Question} from '../question.model';
import {QuestionService} from '../question.service';
import {Router} from '@angular/router';

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
  image: string = null;
  files: FileList;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private questionService: QuestionService)
  {}

  ngOnInit()
  {
    this.form = this.formBuilder.group(
      {
        question: ['', Validators.required],
        type:     ['MULTIPLE_CHOICE', Validators.required],
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
          id:      ['', Validators.required],
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
    q.image = this.image;
    q.question = form.question;
    q.options = form.options;
    q.answer = this.getAnswer(q.options);
    this.questionService.create(q).subscribe(
      () =>
      {
        this.router.navigate(['/question']);
      }
    );
  }

  getFiles(event)
  {
    this.files = event.target.files;
    const reader = new FileReader();
    reader.onload = this._handleReaderLoaded.bind(this);
    reader.readAsBinaryString(this.files[0]);
  }

  _handleReaderLoaded(readerEvt)
  {
    const binaryString = readerEvt.target.result;
    this.image = btoa(binaryString);  // Converting binary string data.
  }

  getAnswer(options)
  {
    return options.find(o => o.checked === true).id;
  }
}
