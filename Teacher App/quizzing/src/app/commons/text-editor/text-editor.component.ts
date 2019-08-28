import {Component, ElementRef, Input, OnInit, Output, ViewChild} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {DialogComponent} from '../dialog/dialog.component';

@Component({
             selector:    'app-text-editor',
             templateUrl: './text-editor.component.html',
             styleUrls:   ['./text-editor.component.css']
           })
export class TextEditorComponent implements OnInit
{

  @ViewChild('textArea', null) textArea: ElementRef;
  @Input() @Output() text = '';
  @Input() placeholder: string;
  @Input() controlName: string;
  @Input() form: FormGroup;
  render = false;

  private modalContent = 'Para adicionar funções na mesma lina do texto como $c = \\pm\\sqrt{a^2 + b^2}$ utilize \\$ "função" \\$.$\\newline{}$' +
                         'Se quiser adicionar uma função en destaque $$\\sum_{i=1}^n(x_i^2 - \\overline{x}^2)$$ utilize \\$\\$ "função" \\$\\$.$\\newline{}$' +
                         '$\\newline{}$ Para ver as funções disponíveis clique $\\href{https://katex.org/docs/supported.html}{aqui}$';

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void { }

  addSnippet(snippet: string)
  {
    const start = this.textArea.nativeElement.selectionStart;
    const end = this.textArea.nativeElement.selectionEnd;
    this.text = this.text.substring(0, start) + snippet + this.text.substring(end);
  }

  openDialog(): void
  {
    const dialogConfig = new  MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      title: 'Editor de Texto',
      paragraph: this.modalContent
    };
    this.dialog.open(DialogComponent, dialogConfig);
  }
}
