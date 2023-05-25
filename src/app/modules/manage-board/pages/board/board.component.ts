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

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss'],
})
export class BoardComponent implements OnInit {
  boardFrom: FormGroup = new FormGroup({
    teamId: new FormControl(null, [Validators.required]),
    userStoryId: new FormControl(null, [Validators.required]),
    taskTeamId: new FormControl(null, [Validators.required]),
    employeeId: new FormControl(null, [Validators.required]),
    date: new FormControl(null, [Validators.required]),
  });
  teams: Team[] = [];
  userStory: UserStory[] = [];
  employees: Employee[] = [];
  teamId: string = '';
  taskTeam: Tasks[] = [];
  userStoryTeam: UserStory[] = [];
  board: IBoard[] = [];

  constructor(
    private teamService: TeamsService,
    private userStoryService: userStoryService,
    private employeesService: EmployeesService,
    private boardService: BoardService,
    private taskTeamService: TeamsTasksService,
    private route: Router
  ) {}

  ngOnInit(): void {
    this.teamService.getAllTeams().subscribe((resp) => {
      this.teams = resp;
    });
  }

  getAllEmployeesTeam() {
    this.employeesService
      .getEmployeesAddToTeam(this.teamId)
      .subscribe((resp) => {
        this.employees = resp;
      });
  }

  getAllTaskTeamByTeam(): void {
    this.taskTeamService
      .getAllTaskTeamByTeamId(this.teamId)
      .subscribe((resp) => {
        this.taskTeam = resp;
      });
  }

  getUserStoryTeam() {
    this.boardService.getUserStoryTeam(this.teamId).subscribe((data) => {
      this.userStory = data;
    });
  }

  selectTeam() {
    this.teamId = this.boardFrom.get('teamId')?.value;
    this.getAllEmployeesTeam();
    this.getAllTaskTeamByTeam();
    this.getUserStoryTeam();
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
