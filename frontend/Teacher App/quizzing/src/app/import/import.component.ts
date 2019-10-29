import {Component, OnInit} from '@angular/core';

@Component({
             selector:    'app-import',
             templateUrl: './import.component.html',
             styleUrls:   ['./import.component.css']
           })
export class ImportComponent implements OnInit
{

  cansubmit = false;
  ignoredWord = '';
  optionsIdentifier = '';
  prefixQuestion = '';
  sufixQuestion = '';
  ignoradePages = '';
  maxQuestionsNumber = '';
  fileBase64 = '';

  constructor() { }

  ngOnInit()
  {
  }

  validateParameter()
  {
    this.fileBase64 = (document.getElementById('fileBase64') as HTMLInputElement).value;

    if (this.ignoredWord === '')
    {
      this.cansubmit = false;
    } else if (this.optionsIdentifier === '')
    {
      this.cansubmit = false;
    } else if (this.prefixQuestion === '')
    {
      this.cansubmit = false;
    } else if (this.sufixQuestion === '')
    {
      this.cansubmit = false;
    } else if (this.ignoradePages === '')
    {
      this.cansubmit = false;
    } else if (this.fileBase64 === '' || this.fileBase64 == null)
    {
      this.cansubmit = false;
    } else if (this.maxQuestionsNumber === '')
    {
      this.cansubmit = false;
    } else
    {
      this.cansubmit = true;
    }
  }

  submit()
  {
    const data =
          {
            ignoredWord:        this.ignoredWord,
            optionsIdentifier:  this.optionsIdentifier,
            prefixQuestion:     this.prefixQuestion,
            sufixQuestion:      this.sufixQuestion,
            ignoradePages:      this.ignoradePages,
            maxQuestionsNumber: this.maxQuestionsNumber,
            fileBase64:         this.fileBase64
          };

    console.log(data);
    // TODO LINK BACKEND
  }

  converToBase64()
  {
    const selectedFile = document.getElementById('inputFile') as HTMLInputElement;
    const f = selectedFile.files[0]; // FileList object
    const reader = new FileReader();
    // Closure to capture the file information.

    // Read in the image file as a data URL.
    reader.readAsBinaryString(f);

    reader.onload = (function(theFile)
    {
      return function(e)
      {
        const binaryData = e.target.result;
        const base64String = window.btoa(binaryData);
        const fileBaseInput = document.getElementById('fileBase64') as HTMLInputElement;
        fileBaseInput.value = base64String;
      };
    })(f);
  }

}
