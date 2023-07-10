import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-edit-estimate-tasks-hu',
  templateUrl: './edit-estimate-tasks-hu.component.html',
  styleUrls: ['./edit-estimate-tasks-hu.component.scss']
})
export class EditEstimateTasksHuComponent {

  taskTeamId: String= '';

  constructor(
    public dialogRef: MatDialogRef<EditEstimateTasksHuComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ){}

  ngOnInit(){
    this.taskTeamId= this.data.tasksTeamId;
   
  }

}
