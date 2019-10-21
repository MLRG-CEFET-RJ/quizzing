import { Component, OnInit } from '@angular/core';
import { SELECT_PANEL_INDENT_PADDING_X } from '@angular/material';

@Component({
	selector: 'app-import',
	templateUrl: './import.component.html',
	styleUrls: ['./import.component.css']
})
export class ImportComponent implements OnInit {
	
	cansubmit:boolean = false;
	ignoredWord:string = '';
	optionsIdentifier:string = '';
	prefixQuestion:string = '';
	sufixQuestion:string = '';
	ignoradePages:string = '';
	maxQuestionsNumber:string = '';
	fileBase64:string = '';

	constructor() { }

	ngOnInit() {
	}

	validateParameter()
	{
		this.fileBase64 = document.getElementById("fileBase64").value;
		
		if(this.ignoredWord === '')
		{
			this.cansubmit = false;
		}
		else if(this.optionsIdentifier === '')
		{
			this.cansubmit = false;
		}
		else if(this.prefixQuestion === '')
		{
			this.cansubmit = false;
		}
		else if(this.sufixQuestion === '')
		{
			this.cansubmit = false;
		}
		else if(this.ignoradePages === '')
		{
			this.cansubmit = false;
		}
		else if(this.fileBase64 === '' || this.fileBase64 == null)
		{
			this.cansubmit = false;
		}
		else if(this.maxQuestionsNumber === '')
		{
			this.cansubmit = false;
		}
		else
		{
			this.cansubmit = true;
		}
	}

	submit()
	{
		var data = 
		{
			ignoredWord:this.ignoredWord,
			optionsIdentifier:this.optionsIdentifier,
			prefixQuestion:this.prefixQuestion,
			sufixQuestion:this.sufixQuestion,
			ignoradePages:this.ignoradePages,
			maxQuestionsNumber:this.maxQuestionsNumber,
			fileBase64:this.fileBase64
		};

		console.log(data);
		//TODO LINK BACKEND
	}

	converToBase64()
	{
		var selectedFile = document.getElementById("inputFile");
		var f = selectedFile.files[0]; // FileList object
		var reader = new FileReader();
		// Closure to capture the file information.
		
		// Read in the image file as a data URL.
		reader.readAsBinaryString(f);
		
		reader.onload = (function(theFile) {
			return function(e) {
			  var binaryData = e.target.result;
			  var base64String = window.btoa(binaryData);
			  var fileBaseInput = document.getElementById("fileBase64");
			  fileBaseInput.value = base64String;
			};
		  })(f);
	}

}
