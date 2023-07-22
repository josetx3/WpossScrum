import { Component, Inject } from '@angular/core';
import { SprintsService } from '../service/sprints.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Sprints } from '../interface/sprints-interfaces';
import { ManageSprintsComponent } from '../manage-sprints/manage-sprints.component';
import { TasksService } from 'src/app/modules/tasks/pages/service/tasks.service';
import { userStoryService } from 'src/app/modules/userStory/pages/service/user-story.service';
import { BoardService } from 'src/app/modules/manage-board/pages/service/board.service';
import { Board } from 'src/app/modules/manage-board/pages/interface/board';

@Component({
  selector: 'app-graphic-burndownchart',
  templateUrl: './graphic-burndownchart.component.html',
  styleUrls: ['./graphic-burndownchart.component.scss']
})
export class GraphicBurndownchartComponent {
  sprintId: String | null='';
  sprint: Sprints[]=[];
  sprintStart: string='';
  sprintEnd: string='';
  sprintDays: number=0;
  nuSprint: string='';
  teamId: string ='';
  UserStorys: any[]=[];
  userStoryId: string='';
  areaId: string='';
  board:Board= [];
  hoursTasks: number=0;
  result:any='';



  view: [number, number] = [900, 500];

 constructor( public sprintService: SprintsService,
              public tasksService: TasksService,
              public boardService: BoardService,
              public dialogRef: MatDialogRef<ManageSprintsComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
                // Object.assign(this, { multi });
  }
  // options
  legend: boolean = true;
  titulo: string= 'Burn down chart';
  autoScale: boolean=true;
  showLabels: boolean = true;
  animations: boolean = true;
  xAxis: boolean = true;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  showXAxisLabel: boolean = true;
  xAxisLabel: string = 'Duracion del sprint (Jornada)';
  yAxisLabel: string = 'Tarbajo realizado (Horas de tareas)';
  timeline: boolean = true;

  colorScheme: any = {
    domain: ['#00A6FE', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5']
  };




  ngOnInit(){
    this.sprintId= this.data.sprintId;
    this.getSprintById()

  }

  getSprintById(){
    this.sprintService.getSprintById(this.sprintId).subscribe(
      {
        next: (resp)=>{
          this.sprint=resp
          this.nuSprint= resp.nuSprint;
          this.areaId= resp.areaId;
          this.teamId= resp.teamId;
          this.sprintDays= parseInt(resp.sprintDaysDate);
          //console.log('datos'+ this.teamId+ this.sprintId)
          this.getStoryUserByTeam()
        }
        ,
        error: ()=>{}
      }
    )
  }

  getStoryUserByTeam(){
    let i:number=1;
    this.tasksService.getStoryUserbyTeam(this.teamId, this.sprintId).subscribe({
      next: (resp)=>{
        this.UserStorys= resp;
        //console.log(this.UserStorys)
        let userStoryId= resp.userStoryId;
        this.UserStorys.forEach((obj:{userStoryId: string})=>{
          userStoryId=obj.userStoryId
          //console.log(userStoryId)
          this.boardService.getBoardByAreaIdTeamIdUserStoryId(this.areaId, this.teamId,userStoryId).subscribe({
            next: (resp)=>{
               this.board[userStoryId] = resp;
            //   console.log(this.board)
               this.board[userStoryId].forEach((obj:{taskHours: string})=>{
                this.hoursTasks+= parseInt(obj.taskHours)
                //console.log(this.hoursTasks)

               })
               //console.log("el numero: "+i)
               if(i===this.UserStorys.length){
                this.dataChart()
               }
            i++;
             },
          error:()=>{}
            })
        })
      }
      ,
      error: ()=>{}
    })

  }

  multi = [
    {
      "name": "Horas estimadas",
      "series": [

      ]
    },

    {
      "name": "Horas reales",
      "series": [
        {
          "name":"0",
          "value": 0
        }
      ]
    },
  ];

  dataChart(){

    //console.log(this.hoursTasks)
    //console.log(this.sprintDays)
    let decr:number= this.hoursTasks/this.sprintDays;
    //console.log(decr)
    let hrsF:number= this.hoursTasks

    for(let i:number=0; i<=this.sprintDays; i++){
        this.multi[0].series.push({name: i+"", value: hrsF})
        //this.multi[1].series.push({name: i, value: hrsF})
        hrsF=hrsF-decr;
    }
    this.result=this.multi
    //console.log(this.multi)
  }





  onSelect(data: any): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data: any): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data: any): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }
}
