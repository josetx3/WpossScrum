import { Component, OnInit } from '@angular/core';
import { Team } from '../interface/team';
import { AreaInterface } from 'src/app/modules/area/pages/Interface/interface-area';
import { TeamsService } from '../service/teams.service';
import { AreaService } from 'src/app/modules/area/pages/service/area.service';
import { MatDialog } from '@angular/material/dialog';
import { TeamManageEditComponent } from '../team-manage-edit/team-manage-edit.component';
import { TeamAddManageComponent } from '../team-add-manage/team-add-manage.component';

@Component({
  selector: 'app-team-manage',
  templateUrl: './team-manage.component.html',
  styleUrls: ['./team-manage.component.scss'],
})
export class TeamManageComponent {
  areas: AreaInterface[] = [];
  team: Team[] = [];
  area: any;
  teamId: string = '';

  constructor(
    private manageTeamsService: TeamsService,
    private areaService: AreaService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getAllTeams();
  }

  getAllTeams() {
    this.manageTeamsService.getAllTeams().subscribe((resp) => {
      resp.forEach((team: { areaId: string | null; }) => {
        this.areaService.getArea(team.areaId).subscribe((area) => {
          team.areaId = area.areaName;
          this.team = resp;
        });
      });
    });
  }

  editTeamModal(teamId: string): void {
    const dialogRef = this.dialog.open(TeamManageEditComponent, {
      width: '500px',
      maxHeight: '600px',
      data: { teamId: teamId },
    });
    dialogRef.afterClosed().subscribe({
      next: (resp) => {
        this.getAllTeams();
      },
    });
  }

  addTeamModal(): void {
    const dialogRef = this.dialog.open(TeamAddManageComponent, {
      width: '500px',
      maxHeight: '600px',
    });
    dialogRef.afterClosed().subscribe({
      next: (resp) => {
        this.getAllTeams();
      },
    });
  }
}
