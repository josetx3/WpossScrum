import { Component, OnInit } from '@angular/core';
import { AreaInterface } from 'src/app/modules/area/pages/Interface/interface-area';
import {FormBuilder} from "@angular/forms";
import { SprintsService } from '../service/sprints.service';
import { AreaService } from 'src/app/modules/area/pages/service/area.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-user-story-sprint',
  templateUrl: './add-user-story-sprint.component.html',
  styleUrls: ['./add-user-story-sprint.component.scss']
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


  constructor(
    private formBuilder: FormBuilder,
    public sprintService: SprintsService,
    public areaService: AreaService,
    private route: ActivatedRoute,
  ) {
  }

  ngOnInit(): void {
    this.areaId = this.route.snapshot.paramMap.get('areaId');
    this.sprintId = this.route.snapshot.paramMap.get('sprintId');
    this.getAllUserStory();
  }
  filterUserStory(){
    this.pointUserStory = this.arrayStory.filter(r => r.userStoryState.userStoryStateName == 'Refinado' && r.userStoryScore == 0);
  }


  getAllUserStory() {
    this.areaService.getArea(this.areaId).subscribe(resp => {
      resp.projects.forEach((projectsArray: { subProjects: any[]; projectName: string | null; }) => {
        projectsArray.subProjects.forEach((subprojectsArray: { userStories: any[]; }) => {
          subprojectsArray.userStories.forEach((userStorys: any) => {
            this.arrayStory.push(userStorys);
            this.projectName=projectsArray.projectName
          })
        })
        this.filterUserStory();
      })
    })

  }
  scoreStory() {
    this.pointUserStory.forEach((resp: { userStoryScore: any; userStoryId: any; }) => {
      let userStoryScore = resp.userStoryScore
      let userStoryId = resp.userStoryId
      const data = {
        userStoryScore: userStoryScore,
        userStoryId: userStoryId
      }
      this.arrayDataStory.push(data)
    })

    this.sprintService.scoreUserStory(this.sprintId, this.arrayDataStory).subscribe({
      next: () => {
        Swal.fire(
          'Hecho!',
          'Presione el Boton (OK) para Continuar',
          'success'
        )
        this.filterUserStory();
      }
    })

  }
}
