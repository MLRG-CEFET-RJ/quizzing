import { Component, OnInit } from '@angular/core';
import {Activity} from './activity.model';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material';
import {DialogComponent} from '../commons/dialog/dialog.component';

@Component({
	selector: 'app-activity',
	templateUrl: './activity.component.html',
	styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {

	activities: Activity[] = [
		{
			id: 1,
			quiz: {
				id:        1,
				name:      'Quiz 1',
				tags:      [
				{
					name: 'tag 1'
				},
				{
          name: 'tag 2'
				},
				{
          name: 'tag 3'
				},
				],
				questions: [
					{
						id:       1,
						question: 'Dadas as retas 𝑟 ≡ 3𝑥 + 𝑦 − 1 = 0 e 𝑠 ≡ 2𝑥 + 𝑚𝑦 − 8 = 0, qual dos seguintes é um\n' +
								'valor de m que faz com que as retas r e s formem um ângulo de 45°',
						type:     'TRUE_OR_FALSE',
						image:    '',
						options:  [],
						rating:   5,
						tags:     null
					},
					{
						id:       2,
						question: 'Dadas as retas 𝑟 ≡ 3𝑥 + 𝑦 − 1 = 0 e 𝑠 ≡ 2𝑥 + 𝑚𝑦 − 8 = 0, qual dos seguintes é um\n' +
								'valor de m que faz com que as retas r e s formem um ângulo de 45°',
						type:     'TRUE_OR_FALSE',
						image:    null,
						options:  [],
						rating:   2,
						tags:     null
					}
				]
			},
			active: true
		},
		{
			id: 2,
			quiz: {
				id:        1,
				name:      'Quiz 2',
				tags:      [
				{
          name: 'tag 1'
				},
				{
          name: 'tag 2'
				},
				{
          name: 'tag 3'
				},
				],
				questions: [
					{
						id:       1,
						question: 'Dadas as retas 𝑟 ≡ 3𝑥 + 𝑦 − 1 = 0 e 𝑠 ≡ 2𝑥 + 𝑚𝑦 − 8 = 0, qual dos seguintes é um\n' +
								'valor de m que faz com que as retas r e s formem um ângulo de 45°',
						type:     'TRUE_OR_FALSE',
						image:    '',
						options:  [],
						rating:   5,
						tags:     null
					},
					{
						id:       2,
						question: 'Dadas as retas 𝑟 ≡ 3𝑥 + 𝑦 − 1 = 0 e 𝑠 ≡ 2𝑥 + 𝑚𝑦 − 8 = 0, qual dos seguintes é um\n' +
								'valor de m que faz com que as retas r e s formem um ângulo de 45°',
						type:     'TRUE_OR_FALSE',
						image:    null,
						options:  [],
						rating:   2,
						tags:     null
					}
				]
			},
			active: false
		}
	];
	constructor(public dialog: MatDialog) { }

	ngOnInit()
	{
	}

	stop(index)
	{


		let matDialogRef = this.openDialog();

   		matDialogRef.afterClosed().subscribe(result => this.d(result, index));
	}

	d(result: boolean, index: number)
	{
		if (result)
		{
			this.activities[index].active = false;
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
