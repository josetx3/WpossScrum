import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Employee } from 'src/app/modules/employees/pages/interface/employee';
import { Tasks } from 'src/app/modules/teams/pages/interface/tasks';
import { Team } from 'src/app/modules/teams/pages/interface/team';
import { UserStory } from 'src/app/modules/userStory/pages/interface/userStory';
import { IBoard } from '../interface/board';
import { TeamsService } from 'src/app/modules/teams/pages/service/teams.service';
import { userStoryService } from 'src/app/modules/userStory/pages/service/user-story.service';
import { EmployeesService } from 'src/app/modules/employees/pages/service/employees.service';
import { BoardService } from '../service/board.service';
import { TeamsTasksService } from 'src/app/modules/teams/pages/service/teams-tasks.service';
import { Router } from '@angular/router';
import {AreaInterface} from "../../../area/pages/Interface/interface-area";
import {AreaService} from "../../../area/pages/service/area.service";

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss'],
})
export class BoardComponent implements OnInit {
  boardFrom: FormGroup = new FormGroup({
    areaId: new FormControl(null, [Validators.required]),
    teamId: new FormControl(null, [Validators.required]),
    userStoryId: new FormControl(null, [Validators.required]),
    taskTeamId: new FormControl(null, [Validators.required]),
    employeeId: new FormControl(null, [Validators.required]),
    date: new FormControl(null, [Validators.required]),
  });
  areaId:any;
  teamId: string = '';
  userStoryTeam: UserStory[] = [];
  board: IBoard[] = [];


  areas: AreaInterface[]= [];
  teams: Team[] = [];
  userStory: any;
  taskTeam: any;
  employees: Employee[] = [];

  constructor(
    private teamService: TeamsService,
    private areaService : AreaService,
    private userStoryService: userStoryService,
    private employeesService: EmployeesService,
    private boardService: BoardService,
    private taskTeamService: TeamsTasksService,
    private route: Router
  ) {}

  ngOnInit(): void {
    this.areaService.getAllArea().subscribe((data) => {
        this.areas = data;
      },
    );
    this.getAllTask();
  }

  selectArea(){
    this.areaId = this.boardFrom.get('areaId')?.value;
    this.boardService.getTeamArea(this.areaId).subscribe(resp =>{
      this.teams = resp;
      console.log("TEAMS:  " + this.teams);
    })
  }

  selectTeam(){
    this.teamId = this.boardFrom.get('teamId')?.value;
    this.userStoryService.getUserStoryToTeam(this.teamId).subscribe(resp =>{
      this.userStory = resp;
    })
    // this.taskTeamService.getAllTaskTeamByTeamId(this.teamId).subscribe(resp =>{
    //   this.taskTeam = resp;
    // })
    this.getAllEmployeesTeam();
  }

  getAllEmployeesTeam() {
    this.employeesService
      .getEmployeesAddToTeam(this.teamId)
      .subscribe((resp) => {
        this.employees = resp;
      });
  }

  getAllTask(){
    this.boardService.getAllTaskTeam().subscribe(resp =>{
      this.taskTeam = resp;
    })
  }


  saveBoard(): void {
    if (this.boardFrom.valid) {
      const data = {
        teamId: this.boardFrom.get('teamId')?.value,
        userStoryId: this.boardFrom.get('userStoryId')?.value,
        taskTeamId: this.boardFrom.get('taskTeamId')?.value,
        employeeId: this.boardFrom.get('employeeId')?.value,
        date: this.boardFrom.get('date')?.value,
      };
      this.boardService.saveBoard(data).subscribe((resp) => {
        this.boardFrom.reset();
        this.route.navigateByUrl('app/board');
      });
    }
  }
}
