import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-curation',
  templateUrl: './curation.component.html',
  styleUrls: ['./curation.component.css']
})
export class CurationComponent implements OnInit {

  @Input() exam;
  @Input() form;
  constructor() { }

  ngOnInit() {
  }

}
