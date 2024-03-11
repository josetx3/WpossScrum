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

  teamId: string='';
  taskTeamId: String= '';
  taskHours: number=0;
  userStoryScore: number=0;
  sumTasksHours: number=0;
  userStoryId: string='';
  taskName: string='';

  FormEditHoursTasks: FormGroup= new FormGroup({
    hoursTasks: new FormControl(null, [Validators.required]),
    taskNameForm: new FormControl(null, [Validators.required])
  }
  );

  constructor(
    public dialogRef: MatDialogRef<EditEstimateTasksHuComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public tasksService: TasksService,
   
  ){}

  ngOnInit(){
    this.teamId= this.data.teamId;
    this.taskTeamId= this.data.taskTeamId;
    this.taskHours= this.data.taskHours;
    this.userStoryScore= this.data.userStoryScore;
    this.userStoryId= this.data.userStoryId;
    this.sumTasksHours=this.data.hoursTot;
    this.taskName= this.data.taskName;
    this.FormEditHoursTasks.patchValue({
      hoursTasks: this.taskHours,
      taskNameForm: this.taskName
    })
  }

  editHoursTasks(){
    if (this.FormEditHoursTasks.valid) {
        let hoursForm= parseInt(this.FormEditHoursTasks.get('hoursTasks')?.value)
        let hoursTasks=  hoursForm-this.taskHours;
        let newHours= this.sumTasksHours+hoursTasks;
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
          const dataTasks={
            taskName: this.FormEditHoursTasks.get('taskNameForm')?.value,
            taskHours: parseInt(this.FormEditHoursTasks.get('hoursTasks')?.value),
            teamId: this.teamId,
            userStoryId: this.userStoryId
            }

            this.tasksService.editTimeTasksByUseStory(this.taskTeamId, dataTasks).subscribe({
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
