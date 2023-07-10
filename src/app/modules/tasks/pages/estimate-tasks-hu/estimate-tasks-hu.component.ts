import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TeamsService } from 'src/app/modules/teams/pages/service/teams.service';
import { TasksService } from '../service/tasks.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-estimate-tasks-hu',
  templateUrl: './estimate-tasks-hu.component.html',
  styleUrls: ['./estimate-tasks-hu.component.scss']
})
export class EstimateTasksHuComponent {

  teams: any='';
  teamId: String | null='';
  userStoryTeam: any='';
  taskByUserStory: any [][]=[];
  //userStoryId: String | null='';


  addEstimateHU: FormGroup = new FormGroup({
    teamId: new FormControl(null, [Validators.required]),
  })

  constructor(
    public teamService: TeamsService,
    public tasksService: TasksService
  ){
  }

  ngOnInit(){
    this.getAllTams()
  }

  getAllTams(){
    this.teamService.getAllTeams().subscribe(
      {
        next: (resp)=>{
            this.teams=resp;
        }
      }
    )
  }

  getStoryUserbyTeam(){
    this.teamId= this.addEstimateHU.get('teamId')?.value;
    this.tasksService.getStoryUserbyTeam(this.teamId).subscribe({
      next: (resp)=>{
        this.userStoryTeam= resp;
       console.log(this.userStoryTeam)
        this.getTasksByUserStory();
      }  
      ,error: (err)=>{
        Swal.fire({
          position: 'top-end',
          icon: 'warning',
          title: 'Este equipo no tiene HU asociadas',
          text:'Para añadirlas vaya a la seccion Gestionar Board',
          showConfirmButton: false,
          timer: 3000,
          toast: true,
          customClass: {
            container: 'my-swal-container',
            title: 'my-swal-title',
            icon: 'my-swal-icon',
          },
          background: '#F5B7B1',
        })
      }
    })
  }

  

  getTasksByUserStory(){
    
    this.userStoryTeam.forEach((element: { userStoryId: any; userStoryScore:number }) => {
      let userStoryId=element.userStoryId;
      
      
      this.tasksService.getTasksByUserStory(this.teamId, userStoryId).subscribe({

        next: (resp)=> {
          this.taskByUserStory[userStoryId]= resp;
        }
      })
    })
   
  }


}
