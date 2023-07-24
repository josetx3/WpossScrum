import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { SprintsService } from '../service/sprints.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-sprint-user-story-manage',
  templateUrl: './edit-sprint-user-story-manage.component.html',
  styleUrls: ['./edit-sprint-user-story-manage.component.scss']
})
export class EditSprintUserStoryManageComponent implements OnInit{
  // idSprint: idSprint, userStoryId: userStoryId, pointsTo:this.pointsTot
  idSprint: String | null= '';
  userStoryId: String | null='';
  pointsTot:number=0;
  points: number=0;
  scorePointSprint: number=0;

  editPointForm: FormGroup = new FormGroup({
    pointsHU: new FormControl(null, [Validators.required])
  })

  constructor(
    public dialogRef: MatDialogRef<EditSprintUserStoryManageComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public sprintService: SprintsService
  ){}

  ngOnInit(): void {
    this.idSprint= this.data.idSprint;
    this.userStoryId= this.data.userStoryId;
    this.pointsTot= this.data.pointsTot;
    this.points=this.data.points;
    this.scorePointSprint= this.data.scorePointSprint;
    this.editPointForm.patchValue({
      pointsHU: this.points
    })
  }

  editPonitHUSprint(){
    if (this.editPointForm.valid) {
        let pointForm= parseInt(this.editPointForm.get('pointsHU')?.value);
        let editPoints= pointForm- this.points;
        let newPoints= this.pointsTot+ editPoints ;
        if(pointForm === 0){
            Swal.fire({
              title: 'Eliminacion de la HU del sprint',
              text: ' Al digitar una puntuación de cero la historia se eliminara del sprint y su estado volvera a REFINADA',
              icon: 'warning',
              showCancelButton: true,
              confirmButtonColor: '#007bff',
              cancelButtonColor: '#F1948A',
              confirmButtonText: 'Sí eliminar!',
              customClass: {
                container: 'my-swal-container',
                title: 'my-swal-title',
                icon: 'my-swal-icon',
              },
              background: '#FFFEFB'
            }).then((result) => {
              if (result.isConfirmed) {
                this.sprintService.deleteSprintUserstory(this.idSprint,this.userStoryId).subscribe({
                  next: (resp)=>{
                    Swal.fire({
                      position: 'top-end',
                      icon: 'success',
                      title: 'Historia de usuario eliminadas del sprint.',
                      showConfirmButton: false,
                      timer: 1500,
                      toast: true,
                      customClass: {
                        container: 'my-swal-container',
                        title: 'my-swal-title',
                        icon: 'my-swal-icon',
                      },
                      background: '#E6F4EA'
                    })
                  }
                })
                this.dialogRef.close();
            }
          })
        } else if(pointForm < 0){
          Swal.fire({
            position: 'top-end',
            icon: 'warning',
            title: 'El numero ingresado es menor que cero, ingrese un número valido.',
            showConfirmButton: false,
            timer: 2500,
            toast: true,
            customClass: {
              container: 'my-swal-container',
              title: 'my-swal-title',
              icon: 'my-swal-icon',
            },
            background: '#FFFEFB',
          })
        }
        else if(newPoints <= this.scorePointSprint)
        {
          const dataSprintUserStory =
          {
            idSprint:this.idSprint,
            userStoryId:this.userStoryId,
            points: this.editPointForm.get('pointsHU')?.value
          }
          this.sprintService.updateSprintUserstory( this.idSprint,this.userStoryId, dataSprintUserStory).subscribe({
            next: (resp)=>{
              Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Puntos de historia de usuario actualizados',
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
            }
          })
        }else{
          Swal.fire({
            position: 'top-end',
            icon: 'warning',
            title: 'La puntuacion ingresada es mayor a los puntos del sprint ideal',
            showConfirmButton: false,
            timer: 3000,
            toast: true,
            customClass: {
              container: 'my-swal-container',
              title: 'my-swal-title',
              icon: 'my-swal-icon',
            },
            background: '#E6F4EA',
          })
        }
    }
  }

}
