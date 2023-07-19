import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators } from '@angular/forms';
import { userStoryService } from '../service/user-story.service';
import { UserStoyStatusService } from '../service/user-stoy-status.service';
import { Router } from '@angular/router';
import { UserStory } from '../interface/userStory';
import { UserStoryComponent } from '../user-story/user-story.component';
import { MatDialogRef } from '@angular/material/dialog';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-user-story-add',
  templateUrl: './user-story-add.component.html',
  styleUrls: ['./user-story-add.component.scss']
})
export class UserStoryAddComponent implements OnInit {

  userStoryForm: FormGroup = new FormGroup({
    subProjectId: new FormControl(null, [Validators.required]),
    userStoryName: new FormControl(null, [Validators.required,Validators.maxLength(30)]),
    userStoryArchive: new FormControl(),
    userStoryStateId: new FormControl(null, [Validators.required]),
    userStoryScore: new FormControl(null, Validators.required),
    fechaMaxima: new FormControl(null, Validators.required)
  });

  userStory: UserStory | any;
  subProjects: any;
  userStoryState: any;

  constructor(
    private userStoryService: userStoryService,
    private subProjectService : userStoryService,
    private userStoryStatusService: UserStoyStatusService,
    private route: Router,
    private dialogRef : MatDialogRef<UserStoryComponent>
  ) {
  }

  ngOnInit(): void {
    this.getAllSubprojects();
    this.getAlluserStoryStatus()
  }

  getAlluserStoryStatus(){
    this.userStoryStatusService.getAlluser_story_status().subscribe(resp => {
      this.userStoryState = resp;
    }
    )
  }

  getAllSubprojects() {
    this.subProjectService.getAllSubprojects().subscribe(resp => {
      this.subProjects = resp;
    })
  }


  saveStory(): void {
    //if (this.userStoryForm.valid) {
    if (this.userStoryForm) {
      const data = {
        subProjectId: this.userStoryForm.get('subProjectId')?.value,
        userStoryArchive: this.userStoryForm.get('userStoryArchive')?.value,
        userStoryName: this.userStoryForm.get('userStoryName')?.value,
        userStoryScore: this.userStoryForm.get('userStoryScore')?.value,
        userStoryStateId: this.userStoryForm.get('userStoryStateId')?.value,
        fechaMaxima: this.userStoryForm.get('fechaMaxima')?.value

      }

      this.userStoryService.saveUser_story(data).subscribe(
        (resp) => {
          console.log(resp)
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Historia de usuario creada',
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
          this.userStoryForm.reset();
          this.dialogRef.close();
        },
      );
    }

  };

  CloseModal(): void {
    this.dialogRef.close();


  }


  upload_image(event: any) {
    let archive = event.target.files
    let reader = new FileReader();

    reader.readAsDataURL(archive[0])
    reader.onloadend = () => {
    }

  }

}
