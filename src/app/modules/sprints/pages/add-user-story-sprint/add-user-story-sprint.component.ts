import {Component, Inject, OnInit} from '@angular/core';
import { AreaInterface } from 'src/app/modules/area/pages/Interface/interface-area';
import { SprintsService } from '../service/sprints.service';
import { AreaService } from 'src/app/modules/area/pages/service/area.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { userStoryService } from 'src/app/modules/userStory/pages/service/user-story.service';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {UserStoryToTeam} from "../../../userStory/pages/interface/userStory";
import {ManageSprintsComponent} from "../manage-sprints/manage-sprints.component";

@Component({
  selector: 'app-add-user-story-sprint',
  templateUrl: './add-user-story-sprint.component.html',
  styleUrls: ['./add-user-story-sprint.component.scss'],
})
export class AddUserStorySprintComponent implements OnInit {
  public areas: AreaInterface[] = [];
  teamId: string = '';
  arrayStory: any[] = [];
  pointUserStory: any;
  sprintId: string | null = '';
  arrayDataStory: any[] = [];
  projectName: string | null = '';
  public sprint: any = [];

  teamStory: UserStoryToTeam[] = [];

  constructor(
    public areaService: AreaService,
    public sprintService: SprintsService,
    private route: ActivatedRoute,
    private userStoyeService: userStoryService,
    @Inject(MAT_DIALOG_DATA) public data:any,
    private dialog: MatDialogRef<ManageSprintsComponent>
  ) {}

  ngOnInit(): void {
    this.teamId = this.data.teamId;
    this.getUserStoryToTeam(this.teamId);
    this.getAllUserStory();
  }

  getAllUserStory():void{
  this.userStoyeService.getAllSubprojects().subscribe(resp =>{
    resp.forEach((item: any) => {
      this.userStoyeService.getUserStoryToTeam(item.teamId).forEach(team =>{
        this.teamId =item.teamName;
      })
    })
    this.pointUserStory = resp;
  })
}

  filterUserStory() {
    this.pointUserStory = this.arrayStory.filter(
      (r) =>
        r.userStoryState.userStoryStateName == 'Refinado' &&
        r.userStoryScore == 0
    );
  }

  getUserStoryToTeam(teamId:string){
    this.userStoyeService.getUserStoryToTeam(teamId).subscribe({
      next: (resp) =>{
        this.teamStory = resp;
      }
    })
  }

  getHuSubproject(subProjectId: string) {
    this.userStoyeService
      .getUserStorySubproject(subProjectId)
      .subscribe((resp) => {
        this.pointUserStory = resp;
      });
  }
  scoreStory() {
    this.pointUserStory.forEach(
      (resp: { userStoryScore: any; userStoryId: any }) => {
        let userStoryScore = resp.userStoryScore;
        let userStoryId = resp.userStoryId;
        const data = {
          userStoryScore: userStoryScore,
          userStoryId: userStoryId,
        };
        this.arrayDataStory.push(data);
      }
    );
    this.sprintService
      .scoreUserStory(this.sprintId, this.arrayDataStory)
      .subscribe({
        next: () => {
          Swal.fire(
            'Hecho!',
            'Presione el Boton (OK) para Continuar',
            'success'
          );
          this.filterUserStory();
        },
      });
  }



}
