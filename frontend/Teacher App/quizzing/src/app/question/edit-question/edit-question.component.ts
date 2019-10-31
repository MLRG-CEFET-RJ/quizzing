import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Question} from '../question.model';
import {Option} from '../../_models/option.model';
import {Tag} from '../../_models/tag.model';
import {QuestionService} from '../question.service';
import {ActivatedRoute, Router} from '@angular/router';

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
  question: Question;
  image: string = null;
  files: FileList;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private questionService: QuestionService)
  {}

  ngOnInit()
  {
    this.questionService.getQuestion(this.route.snapshot.params.id).subscribe(question =>
    {
      this.question = question;
      this.question.options = JSON.parse(question.options.toString());
      this.form = this.formBuilder.group(
        {
          question: [this.question.question, Validators.required],
          type:     [this.question.type, Validators.required],
          image:    [null],
          options:  new FormArray([])
        }
      );
      this.question.options.sort((a, b) => a.letter.charCodeAt(0) - b.letter.charCodeAt(0)).forEach(value => this.getOption(value));
    });
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
    q.id = this.question.id;
    q.tags = this.tags;
    q.type = form.type;
    q.image = this.image;
    q.question = form.question;
    q.options = form.options;
    this.questionService.edit(q).subscribe(
      response =>
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
}
