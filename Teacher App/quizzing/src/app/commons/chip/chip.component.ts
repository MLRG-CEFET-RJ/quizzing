import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {Component, EventEmitter, Output} from '@angular/core';
import {MatChipInputEvent} from '@angular/material/chips';
import {Tag} from '../../_models/tag.model';

@Component({
             selector:    'app-chip',
             templateUrl: './chip.component.html',
             styleUrls:   ['./chip.component.css']
           })
export class ChipComponent
{
  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  tags: Tag[] = [];

  @Output() getTags = new EventEmitter<Tag[]>();

  add(event: MatChipInputEvent): void
  {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim())
    {
      this.tags.push({tag: value.trim()});
    }

    if (input)
    {
      input.value = '';
    }
    this.getTags.emit(this.tags);
  }

  remove(tag: Tag): void
  {
    const index = this.tags.indexOf(tag);

    if (index >= 0)
    {
      this.tags.splice(index, 1);
    }
    this.getTags.emit(this.tags);
  }
}
