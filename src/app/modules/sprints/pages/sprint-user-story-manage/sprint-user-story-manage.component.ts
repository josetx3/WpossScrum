import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SprintsService } from '../service/sprints.service';

import { FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';


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
  points: number=0;

  addUserStoryForm: FormGroup = new FormGroup({
    userStoryId: new FormControl(null, [Validators.required]),
    points: new FormControl(null, [Validators.required])
  })
  
  constructor(
    private route: ActivatedRoute,
    public sprintService: SprintsService,
    ) { }

  ngOnInit() {
    this.sprintId = this.route.snapshot.paramMap.get('sprintId');
    this.getSprintDateById();
  }

  getSprintDateById() {
    this.sprintService.getSprintDateById(this.sprintId).subscribe((resp) => {
      this.SprintDate = resp;
      this.areaId= this.SprintDate.areaId;
      this.teamId= this.SprintDate.teamId;
      this.getUseStoryRef();
    });
  }

  getUseStoryRef(){ //trae hu segun el team y el area
    this.sprintService.getUseStoryRef(this.teamId, this.areaId).subscribe((data)=>{
       this.userStorys=data;
       console.log(this.userStorys)
  });
  }

  addUserStoryToSprint(){
    if (this.addUserStoryForm.valid) {
      const dataSprintUserStory = 
      {
        idSprint:this.sprintId,
        userStoryId:this.addUserStoryForm.get('userStoryId')?.value,
        points: parseInt(this.addUserStoryForm.get('points')?.value)
      }
      console.log(dataSprintUserStory)
      this.sprintService.addUserStoryToSprint(dataSprintUserStory).subscribe((resp)=>{
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Historia de usuario añadido al sprint exitosamente',
          showConfirmButton: false,
          timer: 1500,
          toast: true,
          customClass: {
            container: 'my-swal-container',
            title: 'my-swal-title',
            icon: 'my-swal-icon',
          },
          background: '#E6F4EA',
        })
      this.addUserStoryForm.reset();
      })
 
    };

  }

}
