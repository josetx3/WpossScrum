import {Component, Inject, OnInit} from '@angular/core';
import {SubprojectUserStory} from "../../../userStory/pages/interface/userStory";
import {userStoryService} from "../../../userStory/pages/service/user-story.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {SubprojetcComponent} from "../subprojetc/subprojetc.component";
import {Router} from "@angular/router";
import {UserStoryComponent} from "../../../userStory/pages/user-story/user-story.component";

@Component({
  selector: 'app-subproject-user-story',
  templateUrl: './subproject-user-story.component.html',
  styleUrls: ['./subproject-user-story.component.scss']
})
export class SubprojectUserStoryComponent implements OnInit{
  userStory: SubprojectUserStory[] = [];
  subProjectId: string='';

  constructor(
    private UserStoryService: userStoryService,
    private dialog: MatDialogRef<SubprojetcComponent>,
    private route: Router,
    @Inject(MAT_DIALOG_DATA) public data:any,
    private dialogRef: MatDialogRef<UserStoryComponent>
  ) {}

  ngOnInit() {
    this.subProjectId = this.data.subProjectId;
    this.getUserStoryToSubproyect(this.subProjectId);
  }

  getUserStoryToSubproyect(subProjectId: string){
    this.UserStoryService.getUserStorySubproject(subProjectId).subscribe({
      next: (resp)=>{
        this.userStory = resp
      }
    })
  }

  toUserStory(){
    this.route.navigate(['app/user-story'])
    this.dialog.close();
  }

}
