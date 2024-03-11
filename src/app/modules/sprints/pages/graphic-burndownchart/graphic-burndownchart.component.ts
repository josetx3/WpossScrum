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
  dataFinishTask:any[]=[]



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
          this.getStoryUserByTeam()
        }
        ,
        error: ()=>{}
      }
    )
  }

 async  getStoryUserByTeam(){
    let i:number=1;
    let p:number=0;
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
              console.log(resp);
              
               this.board[userStoryId] = resp;
               this.board[userStoryId].forEach((obj:{taskHours: string,finishDate:any})=>{
                this.hoursTasks+= parseInt(obj.taskHours)
                if(obj.finishDate){
                  this.dataFinishTask[p]=[obj.finishDate,parseInt(obj.taskHours)]
                  p++
                }
                
               })
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
          "name":"1",
          "value": 0
        }
      ]
    },
  ];

 compararFechas(a: [Date, number], b: [Date, number]) {
    return b[0].getTime() - a[0].getTime();
  }

 dataChart(){
  let hoursEnd:number= this.hoursTasks
  let j=1;
  console.log(this.dataFinishTask)

    for(let n=this.dataFinishTask.length-1; n>=0;n--){
      if(this.dataFinishTask[n]===0){
        console.log('datos obviados: '+  n)
      }else{
        const dateObj = new Date(this.dataFinishTask[n][0]);
        const year = dateObj.getFullYear();
        const month = String(dateObj.getMonth() + 1).padStart(2, '0');
        const day = String(dateObj.getDate()+1).padStart(2, '0');
        const formattedDate = `${year}-${month}-${day}`;
        this.dataFinishTask[n][0]=new Date(formattedDate)
      }
    }
    console.log('datos: '+this.dataFinishTask);
    this.dataFinishTask.sort(this.compararFechas);
    console.log('datos: '+this.dataFinishTask );
    this.multi[1].series[0].value=hoursEnd
    for(let n=this.dataFinishTask.length-1; n>=0;n--){
        hoursEnd=hoursEnd-this.dataFinishTask[n][1]
        if(n>=1){
          if(this.dataFinishTask[n][0].getTime()===this.dataFinishTask[n-1][0].getTime()){
          }else{
            console.log('entraa')
            j++;
           this.multi[1].series.push({name: (j)+'', value: hoursEnd})
          }
        }
        if(n===0){
          // if(this.dataFinishTask[n][0].getTime()===this.dataFinishTask[n+1][0].getTime()){
          //   this.multi[1].series[j-1].value=hoursEnd
          // }else{
            console.log('entraa222')
            this.multi[1].series.push({name: (j+1)+'', value: hoursEnd})
            j++;
          //}
        }
    }
    //console.log(this.hoursTasks)
    //console.log(this.sprintDays)
    let decr:number= this.hoursTasks/this.sprintDays;
    console.log(decr)
    let hrsF:number= this.hoursTasks

    for(let i:number=1; i<=this.sprintDays+1; i++){
        this.multi[0].series.push({name: i+"", value: hrsF})
        //this.multi[1].series.push({name: i, value: hrsF})
        hrsF=hrsF-decr;
    }
    this.result=this.multi
    console.log(this.multi)
  }
}
