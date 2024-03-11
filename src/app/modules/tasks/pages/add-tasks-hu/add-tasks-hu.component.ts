import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { TasksService } from '../service/tasks.service';
import Swal from 'sweetalert2';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { EditEstimateTasksHuComponent } from '../edit-estimate-tasks-hu/edit-estimate-tasks-hu.component';
import { SprintsService } from 'src/app/modules/sprints/pages/service/sprints.service';
import { BoardService } from 'src/app/modules/manage-board/pages/service/board.service';

@Component({
  selector: 'app-add-tasks-hu',
  templateUrl: './add-tasks-hu.component.html',
  styleUrls: ['./add-tasks-hu.component.scss']
})
export class AddTasksHuComponent {
  userStoryId: string | null='';
  teamId: string | null='';
  sprintId: string | null='';
  currenDate = new Date();
  sprintDateEnd: string='';
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
    private taskService: TasksService,
    private sprintService: SprintsService,
    private boardService: BoardService,
    private dialog: MatDialog
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
    this.getAllTasksByUserStory();
    this.getSprintById();

  }

  getSprintById(){
    this.sprintService.getSprintById(this.sprintId).subscribe(
      (resp)=>{
        this.sprintDateEnd=resp.sprintEnd
      }
    )
  }

  addTaskToUserStory(){
    if(this.addTaskUS.valid){
      let dateSprintEnd = new Date(this.sprintDateEnd);
      dateSprintEnd.setDate(dateSprintEnd.getDate() + 1);
      if(dateSprintEnd>= this.currenDate ){
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
            console.log(resp)
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
            const data = {
              teamId: resp.teamId,
              userStoryId: resp.userStoryId,
              taskTeamId: resp.taskTeamId,
            }
            this.boardService.saveBoard(data).subscribe((resp) => {
              console.log("se agg bb")
            })
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
      }else{
        Swal.fire({
          position: 'top-end',
          icon: 'warning',
          title: 'Ya no puede agregar tareas puesto que el Sprint ha culminado',
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

  editHoursTasksModal(taskTeamId: string, taskName: string, taskHours: number){
    let dateSprintEnd = new Date(this.sprintDateEnd);
      dateSprintEnd.setDate(dateSprintEnd.getDate() + 1);
      if(dateSprintEnd>= this.currenDate ){
        const configDialog= new MatDialogConfig();
        configDialog.width='500px'
        configDialog.data={taskTeamId: taskTeamId,taskName: taskName,
                          taskHours: taskHours, userStoryScore: this.userStoryScore,
                          userStoryId: this.userStoryId, hoursTot: this.hoursTot, teamId: this.teamId}

        const MatDialog= this.dialog.open(EditEstimateTasksHuComponent,
                          configDialog)

        MatDialog.afterClosed().subscribe(
          (res)=>{
            this.getAllTasksByUserStory();
          }
        )
      }else{
        Swal.fire({
          position: 'top-end',
          icon: 'warning',
          title: 'No puede editar tareas puesto que la fecha del sprint ya terminó',
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