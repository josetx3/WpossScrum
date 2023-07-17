import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators} from "@angular/forms";
import { AreaInterface } from 'src/app/modules/area/pages/Interface/interface-area';
import { Team } from 'src/app/modules/teams/pages/interface/team';
import { IBoard } from '../interface/board';
import { AreaService } from 'src/app/modules/area/pages/service/area.service';
import { userStoryService } from 'src/app/modules/userStory/pages/service/user-story.service';
import { BoardService } from '../service/board.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { BoardEditComponent } from '../board-edit/board-edit.component';
import { BoardComponent } from '../board/board.component';
import { TasksService } from 'src/app/modules/tasks/pages/service/tasks.service';


@Component({
  selector: 'app-board-see',
  templateUrl: './board-see.component.html',
  styleUrls: ['./board-see.component.scss']
})
export class BoardSeeComponent implements OnInit{

  boardFrom: FormGroup = new FormGroup({
    teamId: new FormControl(null, [Validators.required]),
    areaId: new FormControl(null, [Validators.required]),
    userStoryId: new FormControl(null, [Validators.required]),

  })

  teams: Team[] = [];
  teamId: string = '';
  areas: AreaInterface[]=[];
  areaId: string='';
  userStorys: any;
  board: IBoard[]= [];
  taskTeamId: string='';
  taskName: string='';



  constructor(
    private areaServise: AreaService,
    private userStoryService: userStoryService,
    private boardService: BoardService,
    private taskService: TasksService,
    private route: Router,
    private dialog: MatDialog
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
  this.userStoryService.getUserStoryToTeam(this.teamId).subscribe({ //trae todas las hu segun el equipo
    next: (r)=> {
      this.userStorys = r;
    }
  })
}


  filterboard(): void {
    if (this.boardFrom.valid) {
      const data = {
        teamId: this.boardFrom.get('teamId')?.value,
        userStoryId: this.boardFrom.get('userStoryId')?.value,
        areaId: this.boardFrom.get('areaId')?.value,
      }
        this.boardService.getBoardByAreaIdTeamIdUserStoryId(data.areaId, data.teamId, data.userStoryId).subscribe({
         next: (resp)=>{
            this.board = resp;
            this.route.navigateByUrl('app/board');
            this.boardFrom.reset()
          },
          error:
            err => {
              Swal.fire({
                position:'top-end',
                title: 'El tablero que ha intentado buscar no existe.',
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
              this.boardFrom.reset()
            }
        })      
    };
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
              this.boardFrom.reset();
              this.boardService.getAllBoard();
           })

        }
      })

  }

  editBoardModal(idBoard: String) {
    const dialogRef = this.dialog.open(BoardEditComponent, {width: '500px', maxHeight: '600px',   data:{idBoard: idBoard }});
     dialogRef.afterClosed().subscribe(resul => {
    //  this.getAllBoard();
     })
  }

  addBoardModal() {
    const dialogRef = this.dialog.open(BoardComponent, {width: '500px', maxHeight:'600px'});
     dialogRef.afterClosed().subscribe(resul => {
    //  this.getAllBoard();
     })
  }

  finishedTask( taskName: string, taskTeamId: string){
    
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
          taskTeamId: taskTeamId,
          }
        this.taskService.finishedTask(taskTeamId, data).subscribe({
          next: (resp)=>{
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
          }
        })
      
    }
  })

    
  }

  

}
