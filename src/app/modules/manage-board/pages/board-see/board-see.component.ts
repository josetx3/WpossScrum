import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators} from "@angular/forms";
import { AreaInterface } from 'src/app/modules/area/pages/Interface/interface-area';
import { Team } from 'src/app/modules/teams/pages/interface/team';
import { Board, IBoard } from '../interface/board';
import { AreaService } from 'src/app/modules/area/pages/service/area.service';
import { userStoryService } from 'src/app/modules/userStory/pages/service/user-story.service';
import { BoardService } from '../service/board.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { BoardEditComponent } from '../board-edit/board-edit.component';
import { BoardComponent } from '../board/board.component';
import { TasksService } from 'src/app/modules/tasks/pages/service/tasks.service';
import { SprintsService } from 'src/app/modules/sprints/pages/service/sprints.service';
import { Sprints } from 'src/app/modules/sprints/pages/interface/sprints-interfaces';


@Component({
  selector: 'app-board-see',
  templateUrl: './board-see.component.html',
  styleUrls: ['./board-see.component.scss']
})
export class BoardSeeComponent implements OnInit{

  boardFrom: FormGroup = new FormGroup({
    teamId: new FormControl(null, [Validators.required]),
    areaId: new FormControl(null, [Validators.required]),
    sprintId: new FormControl(null, [Validators.required]),

  })

  teams: Team[] = [];
  teamId: string = '';
  areas: AreaInterface[]=[];
  areaId: string='';
  userStorys: any;
  board:Board= [];
  taskTeamId: string='';
  taskName: string='';
  sprints: Sprints[]=[];
  userStoryTeamSprint:any='';



  constructor(
    private areaServise: AreaService,
    private userStoryService: userStoryService,
    private boardService: BoardService,
    private taskService: TasksService,
    private route: Router,
    private dialog: MatDialog,
    private sprintService: SprintsService
  ) {
  }

  ngOnInit(): void {
    // this.getAllBoard();
    this.getAllArea();
  }

  // getAllBoard(){
  //    this.boardService.getAllBoard().subscribe(resp =>{ //Trae todos los tabkeros
  //     this.board = resp;
  //   })
  // }



  getAllArea(){
    this.areaServise.getAllArea().subscribe({  //Trae todas las areas
      next: (r)=> {
        this.areas = r;
      }
    })
  }
  selectArea(){
  this.areaId = this.boardFrom.get('areaId')?.value;
  this.boardService.getTeamArea(this.areaId).subscribe({  //Trae los equipos segun el area
    next: (r)=>{
      this.teams = r;
    }
  })
 }

 selectTeam() {
  this.teamId = this.boardFrom.get('teamId')?.value;
  this.sprintService.getSprintByTeam(this.teamId).subscribe({
    next:(res)=>{
      this.sprints= res;
    },
    error: (res)=>{
      this.sprints=res.null;
    }
  })
}


  filterboard(): void {
    if (this.boardFrom.valid) {
      const data = {
        teamId: this.boardFrom.get('teamId')?.value,
        sprintId: this.boardFrom.get('sprintId')?.value,
        areaId: this.boardFrom.get('areaId')?.value,
      }

      this.taskService.getStoryUserbyTeam(data.teamId,data.sprintId ).subscribe({
        next: (resp)=>{
          this.userStoryTeamSprint= resp;
          //console.log(this.userStoryTeamSprint)
          this.userStoryTeamSprint.forEach((element: { userStoryId: string; })=> {
            let userStoryId=element.userStoryId
            //console.log(userStoryId)
            this.boardService.getBoardByAreaIdTeamIdUserStoryId(data.areaId, data.teamId,userStoryId).subscribe({
              next: (resp)=>{
                //console.log(userStoryId)
                 this.board[userStoryId] = resp;
                 //console.log(this.board)
                 this.route.navigateByUrl('app/board');
                 userStoryId='';
               },
               error:
                 err => {
                   Swal.fire({
                     position:'top-end',
                     title: 'Se debe agregar tablero de la historia vacía',
                     icon: 'warning',
                     confirmButtonColor: '#3085d6',
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
          });
        }
      })
    }
  }

  formatDate(assignDate: string): string {
   if(assignDate){
    const dateObj = new Date(assignDate);

    const year = dateObj.getFullYear();
    const month = String(dateObj.getMonth() + 1).padStart(2, '0');
    const day = String(dateObj.getDate()).padStart(2, '0');


    const formattedDate = `${year}-${month}-${day}`;
    return formattedDate;
   }
    return '';
  }

  getRowClass(status: string): string {

    switch (status) {
      case 'FINALIZADA':
        return 'task-completed';
      case 'EN CURSO':
        return 'task-in-progress';
      case 'PENDIENTE':
        return 'task-pending';
      default:
        return '';
    }
  }

  deleteBoard(id: string): void{
    Swal.fire({
      title: 'Desea eliminar este tablero?',
      text: "La información eliminada no se puede recuperar",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
       confirmButtonText: 'si, eliminar!'
    }).then((result) => {
        if (result.isConfirmed) {
          this.boardService.deleteBoard(id).subscribe(resp =>{
            Swal.fire({
              position: 'top-end',
                icon: 'success',
                title: 'Tablero eliminado',
                showConfirmButton: false,
                timer: 1500,
                toast: true,
                customClass: {
                  container: 'my-swal-container',
                  title: 'my-swal-title',
                  icon: 'my-swal-icon',
                  popup: 'my-swal-popup',
                },
                background: '#E6F4EA',
             })
             this.filterboard();
           })

        }
      })

  }

  editBoardModal(idBoard: String) {
    const dialogRef = this.dialog.open(BoardEditComponent, {width: '500px', maxHeight: '600px',   data:{idBoard: idBoard }});
     dialogRef.afterClosed().subscribe(resul => {
      this.filterboard();
     })
  }



  finishedTask(taskName: string, taskTeamId: string, idBoard: string, taskState: string){
    if(taskState=== 'PENDIENTE'){
      Swal.fire({
        position:'top-end',
        title: 'No es posible finalizar la tarea ya que se encuentra en estado PENDIENTE',
        icon: 'warning',
        confirmButtonColor: '#3085d6',
        showConfirmButton: false,
        timer: 2000,
        toast: true,
        customClass: {
          container: 'my-swal-container',
          title: 'my-swal-title',
          icon: 'my-swal-icon',
        },
        background: '#FFFEFB',
      })
    }else if(taskState=== 'FINALIZADA'){
      Swal.fire({
        position:'top-end',
        title: 'Esta tarea ya se encuentra en estado FINALIZADA.',
        icon: 'warning',
        confirmButtonColor: '#3085d6',
        showConfirmButton: false,
        timer: 2000,
        toast: true,
        customClass: {
          container: 'my-swal-container',
          title: 'my-swal-title',
          icon: 'my-swal-icon',
        },
        background: '#FFFEFB',
      })
    }else{
        Swal.fire({
        title: 'La tarea ha sido finalizada?',
        text: ' Al presionar Sí. El estado de la tarea cambiara a finalizadp',
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#007bff',
        cancelButtonColor: '#F1948A',
        confirmButtonText: 'Sí!',
        customClass: {
          container: 'my-swal-container',
          title: 'my-swal-title',
          icon: 'my-swal-icon',
        },
        background: '#FFFEFB'
      }).then((result) => {
        if (result.isConfirmed) {
          const data={
            taskName: taskName,
            taskTeamId: taskTeamId
            }
          this.taskService.finishedTask(taskTeamId, idBoard, data).subscribe({
            next: (resp)=>{
              console.log(resp)
              Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Tarea finalizada',
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
              this.filterboard();
            }
          })
        }
      })
    }   
  }
}
