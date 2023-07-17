import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { TasksService } from '../service/tasks.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-tasks-hu',
  templateUrl: './add-tasks-hu.component.html',
  styleUrls: ['./add-tasks-hu.component.scss']
})
export class AddTasksHuComponent {
  userStoryId: string | null='';
  teamId: string | null='';
  sprintId: string | null='';
  userStoryCode: string | null='';
  userStoryName: string | null='';
  userStoryScore: number=0;
  tasksByUserStory: any='';
  hoursTot: number=0;

  addTaskUS: FormGroup = new FormGroup({
    taskName: new FormControl(null, [Validators.required]),
    hours: new FormControl(null, Validators.required)
  })

  constructor(
    private route: ActivatedRoute,
    private taskService: TasksService
  ){}

  ngOnInit(){

    this.route.queryParams.subscribe(params => {
      this.userStoryCode = params['userStoryCode']; 
      this.userStoryName= params['userStoryName'];
      this.userStoryScore= +params['userStoryScore'];
      this.teamId= params['TeamId'];
      this.sprintId= params['sprintId'];
      this.userStoryId= params['userStoryID'];
    });
    this.getAllTasksByUserStory()

    console.log('id team: '+this.teamId+'id sprint: '+ this.sprintId)
    console.log('Id: '+ this.userStoryId+'codigo de HU: '+ this.userStoryCode +' Nombre hu: '+ this.userStoryName+'PUNTOS H U: '+this.userStoryScore)
  }

  addTaskToUserStory(){
    if(this.addTaskUS.valid){
      let taskHoursForm=parseInt(this.addTaskUS.get('hours')?.value);
      this.hoursTot+=taskHoursForm;
      if(taskHoursForm<0){
        Swal.fire({
          position: 'top-end',
          icon: 'warning',
          title: 'La horas no pueden ser negativas',
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
      }else if(this.hoursTot<=this.userStoryScore*8){
        const dataTasks={
          taskName: this.addTaskUS.get('taskName')?.value,
          taskHours: parseInt(this.addTaskUS.get('hours')?.value),
          teamId: this.teamId,
          userStoryId: this.userStoryId
      }
      this.taskService.addTaskToUserStory(dataTasks).subscribe({
        next: (resp)=>{
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Tarea agregada a la HU con exito',
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
          this.addTaskUS.reset();
          this.getAllTasksByUserStory();
        }
        ,
        error: (err)=>{}
      })
      }else{
        this.hoursTot=this.hoursTot-taskHoursForm;
        Swal.fire({
          position: 'top-end',
          icon: 'warning',
          title: 'La hora ingresada es mayor a las horas de la HU',
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

  getAllTasksByUserStory(){
    this.taskService.getTasksByUserStory(this.teamId, this.userStoryId).subscribe({
      next: (resp)=>{
        this.tasksByUserStory= resp;
        console.log(this.tasksByUserStory)
        this.tasksByUserStory.forEach( (element:{taskHours: number;})=>{
          this.hoursTot+= element.taskHours
        })
      }
      ,
      error: (err)=>{
        Swal.fire({
          position: 'top-end',
          icon: 'warning',
          title: 'Aún no hay tareas agregadas a la HU',
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
    })
  }

  editHoursTasksModal(taskId: string, taskName: string, taskHours: number){}

}
