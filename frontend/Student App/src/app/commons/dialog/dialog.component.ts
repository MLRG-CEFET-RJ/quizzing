import {Component, Inject, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
             selector:    'app-dialog',
             templateUrl: './dialog.component.html',
             styleUrls:   ['./dialog.component.css']
           })
export class DialogComponent
{
  modalTitle: string;
  paragraph: string;
  action: boolean;
  @Output() result: boolean;

  constructor(
    public dialogRef: MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  )
  {
    this.modalTitle = data.title;
    this.paragraph = data.paragraph;
    this.action = ('action' in data) ? data.action : false;
  }

  onNoClick(): void
  {
    this.dialogRef.close();
  }

}
