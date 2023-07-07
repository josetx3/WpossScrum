import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-sprint-user-story-manage',
  templateUrl: './edit-sprint-user-story-manage.component.html',
  styleUrls: ['./edit-sprint-user-story-manage.component.scss']
})
export class EditSprintUserStoryManageComponent {

  editPointForm: FormGroup = new FormGroup({
    points: new FormControl(null, [Validators.required])
  })

  editPonitHUSprint(){};
}
