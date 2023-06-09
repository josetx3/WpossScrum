import { Component, OnInit, Inject } from '@angular/core';
import {  FormControl, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialogRef } from '@angular/material/dialog';
import { SubprojetcComponent } from '../subprojetc/subprojetc.component';
import Swal from 'sweetalert2';
import { SubprojectService } from '../service/subproject.service';
import { Team } from 'src/app/modules/teams/pages/interface/team';

@Component({
  selector: 'app-subprojetc-edit',
  templateUrl: './subprojetc-edit.component.html',
  styleUrls: ['./subprojetc-edit.component.css']
})
export class SubprojetcEditComponent implements OnInit {
  subprojectEditForm: FormGroup = new FormGroup({
    subprojectName: new FormControl(null, [Validators.required]),
    teamId: new FormControl()
  });
  subproject: any;
  subProjectId: any;
  teams: Team[]=[];

  constructor(
    public subprojectService: SubprojectService,
    private route: ActivatedRoute,
    private dialogRef: MatDialogRef<SubprojetcComponent>,
    private routeurl: Router,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) {
  }

  ngOnInit(): void {
    this.subProjectId = this.data.subProjectId;
    this.getSubProjectById(this.subProjectId)
    this.subProjectId = this.data.subProjectId;
    this.subprojectService.getTeam().subscribe((data) => {
      this.teams = data;
    })
  }

  getSubProjectById(id: string | null) {
    this.subprojectService.getSubProjectById(id).subscribe(resp => {
        this.subproject = resp;

        this.subprojectEditForm.patchValue({
          subprojectName: this.subproject.subProjectName,
        });
      },
      error => {
        console.error(error);
      });
  }
  getAllSubproject(): void {
    this.subprojectService.getAllSubprojects();
  }

  edit() {
    if (this.subprojectEditForm.valid) {
      const data = {
        subProjectName: this.subprojectEditForm.get('subprojectName')?.value,
        teamId: this.subprojectEditForm.get('teamId')?.value
      }
      this.subprojectService.updateSubProject(this.subProjectId, data).subscribe(
        (resp) => {
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Subproyecto editada',
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
          this.subprojectEditForm.reset();

        },
        error => (console.error(error)));
    }
  }
  CloseModal(): void {
    this.dialogRef.close();

  }
}
