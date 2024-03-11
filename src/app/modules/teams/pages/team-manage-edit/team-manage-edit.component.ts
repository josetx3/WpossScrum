import { Component, OnInit, Inject} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import { TeamsService } from '../service/teams.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TeamManageComponent } from '../team-manage/team-manage.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-team-manage-edit',
  templateUrl: './team-manage-edit.component.html',
  styleUrls: ['./team-manage-edit.component.scss']
})
export class TeamManageEditComponent implements OnInit {

  teamsEditForm: FormGroup = new FormGroup({
    teamName: new FormControl(null, [Validators.required]),
    });

    id: string= '';

    constructor(
      private manageTeamService: TeamsService,
      private route: ActivatedRoute,
      private route1: Router,
      @Inject(MAT_DIALOG_DATA) public data: any,
      private dialogRef : MatDialogRef<TeamManageComponent>,
    ) {
    }

    ngOnInit(): void {
      this.id = this.data.teamId;
      this.getTeamById(this.id);

    }

  getTeamById(id: string | null){
    this.manageTeamService.getTeamById(id).subscribe({
      next: (resp)=>{

        this.teamsEditForm.patchValue({
          teamName: resp.teamName,
        })
      }
    })
  }

    edit() {

      if (this.teamsEditForm.valid) {
        const data = {
          teamName: this.teamsEditForm.get('teamName')?.value,

        }
        this.manageTeamService.updateTeam(this.id, data).subscribe(
          (resp) => {
            Swal.fire({
              position: 'top-end',
              icon: 'success',
              title: 'Equipo editado',
              showConfirmButton: false,
              timer: 1500,
              toast: true,
              customClass: {
                container: 'my-swal-container',
                title: 'my-swal-title',
                icon: 'my-swal-icon',
              },
              background: '#E6F4EA',

            })
            this.dialogRef.close();
            this.teamsEditForm.reset();
        });
      }
    }

}
