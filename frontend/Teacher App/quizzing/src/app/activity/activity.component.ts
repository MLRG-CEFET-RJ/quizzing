import {Component, OnInit} from '@angular/core';
import {Activity} from './activity.model';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material';
import {DialogComponent} from '../commons/dialog/dialog.component';
import {ActivityService} from './activity.service';

@Component({
             selector:    'app-activity',
             templateUrl: './activity.component.html',
             styleUrls:   ['./activity.component.css']
           })
export class ActivityComponent implements OnInit
{

  activities: Activity[];

  constructor(public dialog: MatDialog,
              private activityService: ActivityService) { }

  ngOnInit()
  {
    this.activityService.getActivities().subscribe(result => this.activities = result);
  }

  stop(index)
  {
    const matDialogRef = this.openDialog();

    matDialogRef.afterClosed().subscribe(result => {
      this.activityService.stop(this.activities[index]).subscribe( () =>
        this.d(result, index));
    });
  }

  d(result: boolean, index: number)
  {
    if (result)
    {
      this.activities[index].ended = true;
      alert(`${this.activities[index].id} finalizada!`);
    }
  }

  openDialog(): MatDialogRef<DialogComponent, any>
  {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      title:     'Finzalizar Atividade',
      paragraph: 'Tem certeza que deseja finalizar a atividade?',
      action:    true
    };
    dialogConfig.disableClose = true;

    return this.dialog.open(DialogComponent, dialogConfig);
  }

  getReport(activity)
  {
    console.log("gerando relatório");
    console.log(activity);
  }

}
