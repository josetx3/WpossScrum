import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import { TeamsService } from '../service/teams.service';
import { TeamsTasksService } from '../service/teams-tasks.service';
import { MatDialog } from '@angular/material/dialog';
import { Tasks } from '../interface/tasks';
import Swal from 'sweetalert2';
import { TeamTasksEditComponent } from '../team-tasks-edit/team-tasks-edit.component';

@Component({
  selector: 'app-team-tasks',
  templateUrl: './team-tasks.component.html',
  styleUrls: ['./team-tasks.component.scss']
})
export class TeamTasksComponent {

  tasksForm: FormGroup = new FormGroup({
    taskName: new FormControl(null, [Validators.required]),
    teamId: new FormControl(null, [Validators.required]),
    taskTeamId: new FormControl(null,)
  })
  filterTaskTeamForm:FormGroup=new FormGroup({
    teamIdFilter: new FormControl(null, [Validators.required])
  })
  teams: any;
  tasks: Tasks | any;
  tasksTeams: any;
  taskTeamId: any;



  constructor(
    private manageTeamsService: TeamsService,
    private teamTasksService: TeamsTasksService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {

    this.getAllTeamsSelect();
    this.getAllTasksTeams();

  }

  getAllTeamsSelect() {
    this.manageTeamsService.getAllTeams().subscribe(resp => { //Trae todos los equipos
      this.teams = resp

    });
  }

  getAllTasksTeams(){
    this.teamTasksService.getAllTeamTasks().subscribe(resp => { // trae todas las tares por equipo
      this.tasksTeams = resp;

    });
  }


  saveTasks() {
    if (this.tasksForm.valid){
      const data = {
        teamId:this.tasksForm.get('teamId')?.value,
        taskName:this.tasksForm.get('taskName')?.value,
      }
      this.teamTasksService.saveTeamTasks(data).subscribe(
        (resp)=>{
        this.tasksForm.reset();
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Tarea creada',
          showConfirmButton: false,
          timer: 1500,
          toast: true,
          customClass: {
            container: 'my-swal-container',
            title: 'my-swal-title',
            icon: 'my-swal-icon',
            popup: 'my-swal-popup',
          },
        })
        this.getAllTasksTeams();
      })
    }
  }


  deleteTasks(id: string){
    Swal.fire({
      title: 'Desea eliminar tarea?',
      text: "despues de eliminada no podra recuperar los datos",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'si, eliminar!'


      }).then((result) => {
        if (result.isConfirmed) {
          this.teamTasksService.deleteTeamTasks(id).subscribe(resp=>{
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Tarea eliminada',
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
          this.tasksForm.reset();
          this.getAllTasksTeams();
        })
        }
      })

  }

  selectTeamFilter() {
    const teamId = this.filterTaskTeamForm.get('teamIdFilter')?.value
    this.teamTasksService.getAllTaskTeamByTeamId(teamId).subscribe(resp=>{
      this.tasks=resp;
    })

  }

  editTaskModal(taskTeamId: string){
    const dialogRef = this.dialog.open(TeamTasksEditComponent, {width: '500px', data:{taskTeamId: taskTeamId}});
    dialogRef.afterClosed().subscribe({
      next: (resp)=> {
        this.getAllTasksTeams();
      }
    })
  }
}
