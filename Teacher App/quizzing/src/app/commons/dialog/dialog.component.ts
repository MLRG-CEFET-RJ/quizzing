import {Component, Inject, OnInit, Output} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material';

@Component({
             selector:    'app-dialog',
             templateUrl: './dialog.component.html',
             styleUrls:   ['./dialog.component.css']
           })
export class DialogComponent
{
  modalTitle: string;
  @Output() paragraph: string;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any)
  {
    this.modalTitle = data.title;
    this.paragraph = data.text;
  }

}
