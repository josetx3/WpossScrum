import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SprintsService } from '../service/sprints.service';

import { FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-sprint-user-story-manage',
  templateUrl: './sprint-user-story-manage.component.html',
  styleUrls: ['./sprint-user-story-manage.component.scss']
})
export class SprintUserStoryManageComponent {

  sprintId: String | null='';
  // SprintDate: SprintsDate[]=[];
  SprintDate: any;
  userStorys: any;
  areaId: String='';
  teamId: String='';

  addUserStory: FormGroup = new FormGroup({
    userStoryId: new FormControl(null, [Validators.required])
  })
  
  constructor(
    private route: ActivatedRoute,
    public sprintService: SprintsService,
    ) { }

  ngOnInit() {
    this.sprintId = this.route.snapshot.paramMap.get('sprintId');
    this.getSprintDateById();

    console.log(this.sprintId);
    
  }

  getSprintDateById() {
    this.sprintService.getSprintDateById(this.sprintId).subscribe((data) => {
      this.SprintDate = data;
      this.areaId= this.SprintDate.areaId;
      this.teamId= this.SprintDate.teamId;
      this.getUseStoryRef();
    });
    
  }

  getUseStoryRef(){
    this.sprintService.getUseStoryRef(this.areaId, this.teamId).subscribe((data)=>{
       this.userStorys=data;
       console.log(this.userStorys)
  });
  }




  addUserStoryToSprint(){}

}
