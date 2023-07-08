import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TeamsService } from 'src/app/modules/teams/pages/service/teams.service';

@Component({
  selector: 'app-estimate-tasks-hu',
  templateUrl: './estimate-tasks-hu.component.html',
  styleUrls: ['./estimate-tasks-hu.component.scss']
})
export class EstimateTasksHuComponent {

  teams: any='';

  addEstimateHU: FormGroup = new FormGroup({
    teamId: new FormControl(null, [Validators.required])
  })

  constructor(
    public teamService: TeamsService
  ){
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
}
