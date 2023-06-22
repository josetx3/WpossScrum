import Swal from 'sweetalert2';
import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { HttpErrorResponse } from '@angular/common/http';
import { Sprints } from '../interface/sprints-interfaces';
import { SprintsService } from '../service/sprints.service';
import { Team } from 'src/app/modules/teams/pages/interface/team';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AreaService } from 'src/app/modules/area/pages/service/area.service';
import { ManageSprintsComponent } from '../manage-sprints/manage-sprints.component';
import { AreaInterface } from 'src/app/modules/area/pages/Interface/interface-area';

@Component({
  selector: 'app-sprint-add',
  templateUrl: './sprint-add.component.html',
  styleUrls: ['./sprint-add.component.scss']
})
export class SprintAddComponent implements OnInit {

  sprintsForm: FormGroup = new FormGroup({
    teamId: new FormControl(null, [Validators.required]),
    areaId: new FormControl(null, [Validators.required]),
    sprintStart: new FormControl(null, [Validators.required]),
    sprintEnd: new FormControl(null, [Validators.required]),
    sprintDay: new FormControl()
  });

  areas: AreaInterface[]=[];
  teams: Team[]=[];
  areaId: string='';
  teamId: string='';
  sprints: Sprints[]=[];
  sprintDay: any;
  sprintStart: any;
  sprintEnd: any;
  currenDate = new Date();

  constructor(
    private areaService : AreaService,
    private dialogRef: MatDialogRef<ManageSprintsComponent>,
    private sprintService: SprintsService,
  ){
}

  ngOnInit(): void {
      this.getAllArea();
  }

  getAllArea(){
    this.areaService.getAllArea().subscribe((data) => {
      this.areas = data;
    },
  );
  }

  selectArea() {
    this.areaId = this.sprintsForm.get('areaId')?.value;
    this.sprintService.getTeamArea(this.areaId).subscribe(
      (data) => {
        this.teams = data;
      },
    );
  }

  saveSprint(): void {
    if (this.sprintsForm.valid) {
      this.sprintStart = this.sprintsForm.get('sprintStart')?.value;
      this.sprintEnd = this.sprintsForm.get('sprintEnd')?.value;
      this.areaId = this.sprintsForm.get('areaId')?.value;
      this.teamId = this.sprintsForm.get('teamId')?.value;



      let initialDate = new Date(this.sprintStart);
      let endDate = new Date(this.sprintEnd);
      initialDate.setDate(initialDate.getDate() + 1);
      endDate.setDate(endDate.getDate() + 1);

      if (initialDate.getTime() < this.currenDate.getTime()) {
        Swal.fire({
          icon: 'error',
          title: 'Rango de Fechas Invalido',
          text: 'El Sprint no puede comenzar en una fecha Anterior a la Actual'
        })
      } else {
        if (initialDate.getTime() >= endDate.getTime()) {
          Swal.fire({
            icon: 'error',
            title: 'Rango de Fechas Invalido',
            text: 'La Fecha Inicial es mayor o Igual a la Fecha Final'
          })

        } else {
          let milisecondsDay = 24 * 60 * 60 * 1000;
          let elapsedMilliseconds = Math.abs(endDate.getTime() - initialDate.getTime());
          this.sprintDay = Math.round(elapsedMilliseconds / milisecondsDay) + 1;

          const data = {
            teamId: this.sprintsForm.get('teamId')?.value,
            areaId: this.sprintsForm.get('areaId')?.value,
            sprintStart: this.sprintStart,
            sprintEnd: this.sprintEnd,
            sprintDay: this.sprintDay
          }

          this.sprintService.saveSprint(data).subscribe({
            next: () => {
              Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Sprint creado',
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
              this.sprintsForm.reset();
              this.dialogRef.close();
            },
            error: (error: HttpErrorResponse) => {
              if (error.status == 400) {
                Swal.fire({
                  icon: 'error',
                  title: 'Rango de Fechas Invalido',
                  text: 'Ya existe un Sprint Dentro de Este Rango de Fechas'
                });
              }else{
                Swal.fire({
                  icon: 'error',
                  title: 'OOPS',
                  text: 'Error server'
                })
              }

            }
          });

        }

      }
    } else {
      Swal.fire({
        icon: 'warning',
        title: 'Formulario Invalido',
        text: 'Por favor verifique que todos los campos se llenen correctamente'
      })
      this.sprintsForm.markAllAsTouched();
    }
  }

}
