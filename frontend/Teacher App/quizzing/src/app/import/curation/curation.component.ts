import {Component, Input, OnInit} from '@angular/core';
import {MatPaginatorModule} from '@angular/material';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Option} from '../../_models/option.model';
import {Tag} from '../../_models/tag.model';
import {QuestionService} from '../../question/question.service';
import {Question} from '../../question/question.model';
import {QuizService} from '../../quiz/quiz.service';
import {CreateQuizComponent} from '../../quiz/quiz.component';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {QuizDto} from '../../_models/quizDTO.model';
import {Router} from '@angular/router';


@Component({
             selector:    'app-curation',
             templateUrl: './curation.component.html',
             styleUrls:   ['./curation.component.css']
           })
export class CurationComponent implements OnInit
{

  @Input() exam;
  @Input() form;

  pageSize: number = 1;
  currentPage: number = 0;
  totalSize: number;
  question: Question;
  tags: Tag[];
  image: string = null;
  files: FileList;
  quiz = new QuizDto();
  currentOption = [];
  public letters = [];

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private questionService: QuestionService,
              private quizService: QuizService,
              public dialog: MatDialog)
  { }

  ngOnInit()
  {
    this.totalSize = this.exam.length;
    this.extracted();

    this.quiz.ids = [];

    this.form = this.formBuilder.group(
      {
        question: [this.exam[this.currentPage].textoBase, Validators.required],
        type:     ['MULTIPLE_CHOICE', Validators.required],
        image:    [null],
        options:  new FormArray([])
      }
    );

    this.currentOption.sort((a, b) => a.letter.charCodeAt(0) - b.letter.charCodeAt(0)).forEach(value => this.getOption(value));
  }

  private extracted()
  {
    this.currentOption = [];
    for (let index = 0; index < this.exam[this.currentPage].alternativas.length; index++)
    {
      let id = index + 1;
      let option: Option = {
        option:  this.exam[this.currentPage].alternativas[index].conteudo,
        id:      "" + id + "",
        letter:  this.exam[this.currentPage].alternativas[index].letra,
        checked: false
      };
      this.currentOption.push(option);
    }
  }

  pageEventHandle($event)
  {
    this.currentPage = $event.pageIndex;
    this.extracted();
    this.tags = null;
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
          checked: [false, Validators.required]
        })
      );
    }
  }

  addTags(t: Tag[])
  {
    this.tags = t;
  }

  getAnswer(options)
  {
    return options.find(o => o.checked === true).id;
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
      response =>
      {
        this.quiz.ids.push({id: response.id});
        //this.exam.splice(this.currentPage,1);
        //console.log(this.exam);
      }
    );
  }

  _handleReaderLoaded(readerEvt)
  {
    const binaryString = readerEvt.target.result;
    this.image = btoa(binaryString);  // Converting binary string data.
  }

  getFiles(event)
  {
    this.files = event.target.files;
    const reader = new FileReader();
    reader.onload = this._handleReaderLoaded.bind(this);
    reader.readAsBinaryString(this.files[0]);
  }

  submitQuiz()
  {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '250px';
    dialogConfig.data = {
      name: null,
      tags: null
    };
    const dialogRef = this.dialog.open(CreateQuizComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result =>
                                      {
                                        this.quiz.name = result.name;
                                        this.quiz.tags = result.tags;
                                        //this.totalSize--;
                                        //console.log(this.quiz);
                                        this.quizService.create(this.quiz).subscribe( () => this.router.navigate(['/quiz']));
                                      });
  }
}
