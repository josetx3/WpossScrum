import {Component, OnInit, Inject,} from '@angular/core';
import { FormControl, FormGroup, Validators} from "@angular/forms";
import { TeamsTasksService } from '../service/teams-tasks.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TeamTasksComponent } from '../team-tasks/team-tasks.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-team-tasks-edit',
  templateUrl: './team-tasks-edit.component.html',
  styleUrls: ['./team-tasks-edit.component.scss']
})
export class TeamTasksEditComponent implements OnInit {
  tasksFormEdit: FormGroup = new FormGroup({
    taskName: new FormControl(null, [Validators.required])
  })
  id: any;

  constructor(
    private teamTasksService: TeamsTasksService,
    private route: ActivatedRoute,
    private route1: Router,
    private dialogRef: MatDialogRef<TeamTasksComponent>,
    @Inject (MAT_DIALOG_DATA) public data: any,
    ) {
  }

  ngOnInit(): void {

    this.id = this.data.taskTeamId;
    this.getTaskTeamById(this.id);
  }

  getTaskTeamById(id: string | null) {
    this.teamTasksService.getTeamTasks(id).subscribe(resp => {
      this.tasksFormEdit.patchValue({
        taskName: resp.taskName
      })
    });

  }

  editTasks() {
    if (this.tasksFormEdit.valid) {
      const data = {
        taskName: this.tasksFormEdit.get('taskName')?.value,
      }
      this.teamTasksService.updateTeamTasks(this.id, data).subscribe((resp => {
            this.tasksFormEdit.reset();
            Swal.fire({
              position: 'top-end',
                icon: 'success',
                title: 'Tarea editada',
                showConfirmButton: false,
                timer: 1500,
                toast: true,
                customClass: {
                  container: 'my-swal-container',
                  title: 'my-swal-title',
                  icon: 'my-swal-icon',
                  popup: 'my-swal-popup',
                },
            })
            this.dialogRef.close();
          }
        )
      )
    }
  }
}
