import { Component, OnInit } from '@angular/core';
import { AreaInterface } from 'src/app/modules/area/pages/Interface/interface-area';
import { SprintsService } from '../service/sprints.service';
import { AreaService } from 'src/app/modules/area/pages/service/area.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { userStoryService } from 'src/app/modules/userStory/pages/service/user-story.service';

@Component({
  selector: 'app-add-user-story-sprint',
  templateUrl: './add-user-story-sprint.component.html',
  styleUrls: ['./add-user-story-sprint.component.scss'],
})
export class AddUserStorySprintComponent implements OnInit {
  public areas: AreaInterface[] = [];
  areaId: string | null = '';
  teamId: string | null = '';
  arrayStory: any[] = [];
  pointUserStory: any;
  sprintId: string | null = '';
  arrayDataStory: any[] = [];
  projectName: string | null = '';

  public sprint: any = [];

  constructor(
    public sprintService: SprintsService,
    public areaService: AreaService,
    private route: ActivatedRoute,
    private userStoyeService: userStoryService
  ) {}

  ngOnInit(): void {
    this.areaId = this.route.snapshot.paramMap.get('areaId');
    this.sprintId = this.route.snapshot.paramMap.get('sprintId');
    //this.getAllUserStory();
    //this.getAllSprint();
  }

  //?

  agetAllUserStory() {
    this.areaService.getArea(this.areaId).subscribe((resp) => {
      console.log("RTA:   " + JSON.stringify(resp));
      resp.projects.forEach(
        (projectsArray: { subProjects: any[]; projectName: string | null }) => {
          console.log("ASD:   " + projectsArray);

          projectsArray.subProjects.forEach(
            (subprojectsArray: { userStories: any[] }) => {
              subprojectsArray.userStories.forEach((userStorys: any) => {
                this.arrayStory.push(userStorys);
                this.projectName = projectsArray.projectName;
              });
            }
          );
          this.filterUserStory();
        }
      );
    });
  }
//?
   //! ================================================================================================================================================================ !\\
/*
   getAllSprint(){
    this.userStoyeService.getUserStoryToTeam(this.sprintId).subscribe({
      next: (resp) => {
        this.sprint = resp;
        console.log("RESP:  " + JSON.stringify(resp))
        console.log("ASDASD:  " + resp.teamId)
      }
    })
   }
   getUserStoryId(){
    this.userStoyeService.getAllUser_story
   }
   */
    //! ================================================================================================================================================================ !\\




  filterUserStory() {
    this.pointUserStory = this.arrayStory.filter(
      (r) =>
        r.userStoryState.userStoryStateName == 'Refinado' &&
        r.userStoryScore == 0
    );
  }

  //! ================================================================================================================================================================ !\\
  getHuSubproject(subProjectId: string){
    this.userStoyeService.getUserStorySubproject(subProjectId).subscribe((resp) => {
      this.pointUserStory = resp;
      console.log("RESP:    " + resp);

    })
  }
  //! ================================================================================================================================================================ !\\



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
