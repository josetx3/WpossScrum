import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserStory } from '../interface/userStory';
import { userStoryService } from '../service/user-story.service';
import { MatDialog } from '@angular/material/dialog';
import { SubprojectService } from 'src/app/modules/proyect/pages/service/subproject.service';
import { UserStoryAddComponent } from '../user-story-add/user-story-add.component';
import { UserStoryManagementComponent } from '../user-story-management/user-story-management.component';
import { auto } from '@popperjs/core';

@Component({
  selector: 'app-user-story',
  templateUrl: './user-story.component.html',
  styleUrls: ['./user-story.component.scss']
})
export class UserStoryComponent implements OnInit {

  userStoryForm: FormGroup = new FormGroup({
    subProjectId: new FormControl(null, [Validators.required]),
    userStoryName: new FormControl(null, [Validators.required]),
    userStoryArchive: new FormControl(null, [Validators.required]),
    userStoryStateId: new FormControl(null, [Validators.required]),
    userStoryStateName: new FormControl(null, [Validators.required]),
    userStoryScore: new FormControl(null, Validators.required),
    fechaMaxima: new FormControl(null, [Validators.required]),
  });

  userStory: UserStory | any;
  subProjects: any;
  userStoryId: string = '';



  constructor(
    private userStoryService: userStoryService,
    private subProjectService: SubprojectService,
    private dialog: MatDialog
  ){}

  ngOnInit(): void {
      this.getAllUserStory();
  }


  getAllUserStory() {
    this.userStoryService.getAllUser_story().subscribe((resp) => {
      this.userStory = resp;
    });
  }

  getAllSubprojects() {
    this.subProjectService.getAllSubprojects().subscribe((resp) => {
      this.subProjects = resp;
    });
  }

  saveStory(): void {
    if (this.userStoryForm.valid) {
      const data = {
        subProjectId: this.userStoryForm.get('subProjectId')?.value,
        userStoryArchive: this.userStoryForm.get('userStoryArchive')?.value,
        userStoryName: this.userStoryForm.get('userStoryName')?.value,
        userStoryScore: this.userStoryForm.get('userStoryScore')?.value,
        userStoryStateId: this.userStoryForm.get('userStoryStateId')?.value,
        fechaMaxima: this.userStoryForm.get('fechaMaxima')?.value,
        userStoryStateName: this.userStoryForm.get('userStoryStateName'),
      };
      this.userStoryService.saveUser_story(data).subscribe((resp) => {
        this.userStoryForm.reset();
        this.getAllUserStory();
      });
    }
  }

  upload_image(event: any) {
    let archive = event.target.files;
    let reader = new FileReader();

    reader.readAsDataURL(archive[0]);
    reader.onloadend = () => {};
  }

  addUserStoryModal(): void {
    const dialogRef = this.dialog.open(UserStoryAddComponent, {
    });

    dialogRef.afterClosed().subscribe((resul) => {
      this.getAllUserStory();
    });
  }

  editUserStoryModal(userStoryId: string): void {
    const dialogRef = this.dialog.open(UserStoryManagementComponent, {
      data: { userStoryId: userStoryId },
    });

    dialogRef.afterClosed().subscribe({
      next: (resp) => {
        this.getAllUserStory();
      },
    });
  }

}
