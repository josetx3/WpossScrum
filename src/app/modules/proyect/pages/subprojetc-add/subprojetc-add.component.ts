import {Component, OnInit} from '@angular/core';
import { FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { ProjectService } from '../service/project.service';
import Swal from "sweetalert2";
import {MatDialogRef} from '@angular/material/dialog';
import { Subproject } from '../interface/subprojects';
import { SubprojectService } from '../service/subproject.service';
import { SubprojetcComponent } from '../subprojetc/subprojetc.component';
import { Team } from 'src/app/modules/teams/pages/interface/team';
import { TeamsService } from 'src/app/modules/teams/pages/service/teams.service';


@Component({
  selector: 'app-subprojetc-add',
  templateUrl: './subprojetc-add.component.html',
  styleUrls: ['./subprojetc-add.component.css']
})
export class SubprojetcAddComponent implements OnInit {

  subprojectAddForm: FormGroup = new FormGroup({
    subProjectName: new FormControl(null, [Validators.required]),
    projectId: new FormControl(null, [Validators.required]),
    teamId: new FormControl(null, [Validators.required])
  });

  subproject: Subproject[]=[] ;
  teams: Team[] = [];
  projects: any;

  constructor(
    private subprojectService: SubprojectService,
    private proyectService: ProjectService,
    private route: Router,
    private dialogRef: MatDialogRef<SubprojetcComponent>,
    private teamService: TeamsService
  ) {
  }

  ngOnInit(): void {

    this.subprojectService.getProyecto().subscribe((data) =>{
      this.projects = data;
    })
    this.subprojectService.getTeam().subscribe((data) => {
      this.teams = data;
    })

  }



  saveSubProject(): void {
    if (this.subprojectAddForm.valid) {
      const data = {
        subProjectName: this.subprojectAddForm.get('subProjectName')?.value,
        projectId:this.subprojectAddForm.get('projectId')?.value,
        teamId: this.subprojectAddForm.get('teamId')?.value

      }

      this.subprojectService.saveSubProject(data).subscribe((resp) => {
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Subproyecto creado',
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

        this.subprojectAddForm.reset()
        this.dialogRef.close();
      },);
    }
  }
 closeModal(): void {
  this.dialogRef.close();
 }


}
