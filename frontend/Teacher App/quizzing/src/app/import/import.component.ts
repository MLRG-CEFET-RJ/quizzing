import {Component, OnInit} from '@angular/core';
import {ImportService} from './import.service';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
             selector:    'app-import',
             templateUrl: './import.component.html',
             styleUrls:   ['./import.component.css']
           })
export class ImportComponent implements OnInit
{

  cansubmit = false;
  curation = false;
  ignoredWord = '421_EXAME POSCOMP_NS_22/8/201711:56:14", "Execuçãoo: Fundatec", "EXAME POSCOMP 2017';
  optionsIdentifier = 'A),B),C),D),E)';
  prefixQuestion = 'QUESTÃO ';
  sufixQuestion = ' ';
  ignoradePages = '1,2';
  maxQuestionsNumber = '70';
  fileBase64 = '';

  exam;
  form: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private importService: ImportService) { }

  ngOnInit()
  {
    this.form = this.formBuilder.group(
    {
      questions:  new FormArray([])
    }
  );
  }
  get f() { return this.form.controls; }

  get t() { return this.f.questions as FormArray; }

  addQuestion(question: any)
  {
    for (let i = this.t.length; i < this.exam.length; i++)
    {
      this.t.push(this.formBuilder.group(
        {
          textoBase:  [question.textoBase, Validators.required],
        })
      );
    }
  }
  validateParameter()
  {
    this.fileBase64 = (document.getElementById('fileBase64') as HTMLInputElement).value;

    if (this.ignoredWord === '')
    {
      this.cansubmit = false;
    }
    else if (this.optionsIdentifier === '')
    {
      this.cansubmit = false;
    }
    else if (this.prefixQuestion === '')
    {
      this.cansubmit = false;
    }
    else if (this.sufixQuestion === '')
    {
      this.cansubmit = false;
    }
    else if (this.ignoradePages === '')
    {
      this.cansubmit = false;
    }
    else if (this.fileBase64 === '' || this.fileBase64 == null)
    {
      this.cansubmit = false;
    }
    else
    {
      this.cansubmit = this.maxQuestionsNumber !== '';
    }
  }

  submit()
  {
    this.fileBase64 = (document.getElementById('fileBase64') as HTMLInputElement).value;
    const data =
            {
              ignoredWords:         this.ignoredWord,
              optionsIdentifier:    this.optionsIdentifier,
              questionPrefix:       this.prefixQuestion,
              questionSuffix:       this.sufixQuestion,
              ignoredPages:         this.ignoradePages,
              maxQuestionsNumber:   this.maxQuestionsNumber,
              file:                 this.fileBase64
            };

    this.importService.import(data).subscribe( res =>
                                               {
                                                 this.curation = true;
                                                 this.exam = res;
                                                 this.addQuestion(this.exam);
                                               });
  }

  converToBase64()
  {
    const selectedFile = document.getElementById('inputFile') as HTMLInputElement;
    const f = selectedFile.files[0]; // FileList object
    const reader = new FileReader();
    // Closure to capture the file information.

    // Read in the image file as a data URL.
    reader.readAsBinaryString(f);

    reader.onload = (function (theFile)
    {
      return function (e)
      {
        const binaryData = e.target.result;
        const base64String = window.btoa(binaryData);
        const fileBaseInput = document.getElementById('fileBase64') as HTMLInputElement;
        fileBaseInput.value = base64String;
      };
    })(f);
  }

}
