import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TeamsService } from 'src/app/modules/teams/pages/service/teams.service';
import { TasksService } from '../service/tasks.service';
import Swal from 'sweetalert2';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DialogConfig } from '@angular/cdk/dialog';
import { EditEstimateTasksHuComponent } from '../edit-estimate-tasks-hu/edit-estimate-tasks-hu.component';
import { SprintsService } from 'src/app/modules/sprints/pages/service/sprints.service';

@Component({
  selector: 'app-estimate-tasks-hu',
  templateUrl: './estimate-tasks-hu.component.html',
  styleUrls: ['./estimate-tasks-hu.component.scss']
})
export class EstimateTasksHuComponent {

  teams: any='';
  teamId: String | null='';
  sprintId:String | null='';
  teamIDb: String | null='';
  userStoryTeamSprint: any='';
  taskByUserStory: any [][]=[];
  sumTasksHourBy: number[]=[];
  sprints: any='';
  //userStoryId: String | null='';


  filterHU: FormGroup = new FormGroup({
    teamId: new FormControl(null, [Validators.required]),
    sprintId: new FormControl(null, [Validators.required])
  })

  constructor(
    public sprintService: SprintsService,
    public teamService: TeamsService,
    public tasksService: TasksService,
    private dialog: MatDialog
  ){
  }

  ngOnInit(){
    this.getAllTeams()
  }

  getAllTeams(){
    this.teamService.getAllTeams().subscribe(
      {
        next: (resp)=>{
            this.teams=resp;
        }
      }
    )
  }

  selectedTeam(){
    this.teamIDb='';
    this.teamIDb= this.filterHU.get('teamId')?.value;
    console.log(this.teamIDb);
    this.sprintService.getSprintByTeam(this.teamIDb).subscribe({
      next: (res)=> {
        this.sprints= res;
      },
      error: (err)=>{
        this.sprints= null;
      }
    })
  }

  getStoryUserbyTeam(){
    this.teamId= this.filterHU.get('teamId')?.value;
    this.sprintId= this.filterHU.get('sprintId')?.value;
    this.tasksService.getStoryUserbyTeam(this.teamId,this.sprintId ).subscribe({
      next: (resp)=>{
        this.userStoryTeamSprint= resp;
        console.log(this.userStoryTeamSprint)
      }  
      ,error: (err)=>{
        Swal.fire({
          position: 'top-end',
          icon: 'warning',
          title: 'Este equipo no tiene HU asociadas',
          showConfirmButton: false,
          timer: 3000,
          toast: true,
          customClass: {
            container: 'my-swal-container',
            title: 'my-swal-title',
            icon: 'my-swal-icon',
          },
          background: '#FFFEFB',
        })
      }
    })
  }

  // getTasksByUserStory(){
    
  //   this.userStoryTeamSprint.forEach((element: { userStoryId: any; }) => {
  //     let userStoryId=element.userStoryId;
      
  //     this.tasksService.getTasksByUserStory(this.teamId, userStoryId).subscribe({

  //       next: (resp)=> {
  //         this.taskByUserStory[userStoryId]= resp;
  //         // this.sumTasksHourBy+=this.taskByUserStory[userStoryId];
  //         // console.log(this.taskByUserStory[userStoryId])
  //         let size= this.taskByUserStory[userStoryId].length;
  //         this.sumTasksHourBy[userStoryId]=0;
  //         for(let i=0; i<size; i++){
  //           this.taskByUserStory[userStoryId][i]
  //           this.sumTasksHourBy[userStoryId]+=this.taskByUserStory[userStoryId][i].taskHours;
  //         }

  //       }
  //     })
  //   })
  // }


  // editTimeTasksByUseStory(tasksTeamId: string,taskName: string, taskHours: number, userStoryScore: number, userStoryId: any ){
  //   const dialogConfig= new MatDialogConfig();

  //   dialogConfig.width='500px';
  //   dialogConfig.data={ 
  //       tasksTeamId:tasksTeamId,
  //       taskName: taskName,
  //       taskHours: taskHours,
  //       userStoryScore: userStoryScore, 
  //       sumTasksHourBy: this.sumTasksHourBy,
  //       userStoryId: userStoryId
  //     }
  //   console.log(dialogConfig.data)
  //   const dialogRef = this.dialog.open(
  //     EditEstimateTasksHuComponent,
  //     dialogConfig
  //   );

  //   dialogRef.afterClosed().subscribe({
  //       next: (resp)=>{
     
  //       }
  //   })

  // }

  
}
