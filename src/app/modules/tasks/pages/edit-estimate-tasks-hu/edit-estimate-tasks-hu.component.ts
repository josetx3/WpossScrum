import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TasksService } from '../service/tasks.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-estimate-tasks-hu',
  templateUrl: './edit-estimate-tasks-hu.component.html',
  styleUrls: ['./edit-estimate-tasks-hu.component.scss']
})
export class EditEstimateTasksHuComponent {

  taskTeamId: String= '';
  taskHours: number=0;
  userStoryScore: number=0;
  sumTasksHourBy: number=0;
  userStoryId: string='';
  taskName: string='';

  FormEditHoursTasks: FormGroup= new FormGroup({
    hoursTasks: new FormControl(null, [Validators.required])
  }
  );

  constructor(
    public dialogRef: MatDialogRef<EditEstimateTasksHuComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public tasksService: TasksService,
   
  ){}

  ngOnInit(){
    this.taskTeamId= this.data.tasksTeamId;
    this.taskHours= this.data.taskHours;
    this.userStoryScore= this.data.userStoryScore;
    this.userStoryId= this.data.userStoryId;
    this.sumTasksHourBy=this.data.sumTasksHourBy[this.userStoryId];
    this.taskName= this.data.taskName;
    this.FormEditHoursTasks.patchValue({
      hoursTasks: this.taskHours
    })
  }

  editHoursTasks(){
    if (this.FormEditHoursTasks.valid) {
        let hoursForm= parseInt(this.FormEditHoursTasks.get('hoursTasks')?.value)
        let hoursTasks=  hoursForm-this.taskHours;
        let newHours= this.sumTasksHourBy+hoursTasks;
        if(hoursForm< 0){
          Swal.fire({
            position: 'top-end',
            icon: 'warning',
            title: 'La horas ingresadas son incorrectas.',
            showConfirmButton: false,
            timer: 1500,
            toast: true,
            customClass: {
              container: 'my-swal-container',
              title: 'my-swal-title',
              icon: 'my-swal-icon',
            },
            background: '#FFFEFB'
          })
        }else if(newHours< this.userStoryScore*8){
            const data={
              taskHours: hoursForm,
              taskTeamId: this.taskTeamId,
              taskName: this.taskName
            }
            this.tasksService.editTimeTasksByUseStory(this.taskTeamId,data).subscribe({
              next: (resp)=>{
                Swal.fire({
                  position: 'top-end',
                  icon: 'success',
                  title: 'Hora editada con exito',
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
              ,
              error: (err)=>{
              }
            })
            this.dialogRef.close();
        }else{
          Swal.fire({
            position: 'top-end',
            icon: 'warning',
            title: 'La horas ingresadas son mayores a las horas de la HU',
            showConfirmButton: false,
            timer: 1500,
            toast: true,
            customClass: {
              container: 'my-swal-container',
              title: 'my-swal-title',
              icon: 'my-swal-icon',
            },
            background: '#FFFEFB'
          })
        }
    }
   
  }


  


}
