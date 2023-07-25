import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators} from "@angular/forms";
import { UserStory } from 'src/app/modules/userStory/pages/interface/userStory';
import { IBoard } from '../interface/board';
import { TeamsService } from 'src/app/modules/teams/pages/service/teams.service';
import { EmployeesService } from 'src/app/modules/employees/pages/service/employees.service';
import { BoardService } from '../service/board.service';
import { TeamsTasksService } from 'src/app/modules/teams/pages/service/teams-tasks.service';
import { Router } from '@angular/router';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BoardComponent } from '../board/board.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-board-edit',
  templateUrl: './board-edit.component.html',
  styleUrls: ['./board-edit.component.scss']
})
export class BoardEditComponent implements OnInit {


  boardEditFrom: FormGroup = new FormGroup({
    teamId: new FormControl({ value: null, disabled: true }),
    userStoryId: new FormControl({ value: null, disabled: true }),
    taskTeamId: new FormControl({ value: null, disabled: true }),
    employeeId: new FormControl(null, [Validators.required]),
  })

  teams:any;
  userStory: any;
  employees: any;
  teamId: string = '';
  taskTeam: any;
  userStoryTeam: UserStory[] = [];
  board: IBoard[]= [];
  idBoard: string = '';


  constructor(
    private teamService: TeamsService,
    private employeesService: EmployeesService,
    private boardService: BoardService,
    private taskTeamService: TeamsTasksService,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<BoardComponent>,
  ) {
  }

  ngOnInit(): void {

    this.idBoard = this.data.idBoard;
    this.boardService.getAllBoardById(this.idBoard).subscribe({
      next: (resp)=>{
        this.boardEditFrom.patchValue({
          employeeId : resp.employeeId,
          userStoryId : resp.userStoryId,
          teamId : resp.teamId,
          taskTeamId : resp.taskTeamId,
          date : resp.date
      })
      }
    });

    this.boardService.getEmployees().subscribe((data) => {
      this.employees = data;

    })


    this.boardService.getAllUserStory().subscribe((data) => {
      this.userStory = data;
    })


    this.boardService.getAllTeam().subscribe((data)=> {
      this.teams = data;
    })
    this.boardService.getAllTaskTeam().subscribe((data)=> {
      this.taskTeam = data;
    })
    //console.log(this.idBoard);
  }



  editBoard(): void {
    if (this.boardEditFrom.valid) {
      const data = {
        teamId: this.boardEditFrom.get('teamId')?.value,
        userStoryId: this.boardEditFrom.get('userStoryId')?.value,
        taskTeamId: this.boardEditFrom.get('taskTeamId')?.value,
        employeeId: this.boardEditFrom.get('employeeId')?.value,
      }
      this.boardService.updateBoard(this.idBoard, data).subscribe((resp) => {
          this.boardEditFrom.reset()
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Empleado asignado con exito',
            showConfirmButton: false,
            timer: 1500
          })
          this.dialogRef.close();
         
        },
      );
    }
  }
  CloseModal(): void {
    this.dialogRef.close();

  }


}
