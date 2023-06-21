import { Component, Inject, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { userStoryService } from '../service/user-story.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserStoyStatusService } from '../service/user-stoy-status.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { UserStoryComponent } from '../user-story/user-story.component';

@Component({
  selector: 'app-user-story-management',
  templateUrl: './user-story-management.component.html',
  styleUrls: ['./user-story-management.component.scss']
})
export class UserStoryManagementComponent implements OnInit {
  userStoryForm: FormGroup = new FormGroup({
    userStoryName: new FormControl(null, [Validators.required,Validators.maxLength(30)]),
    userStoryArchive: new FormControl(null, []),
    userStoryStateId: new FormControl(null, [Validators.required]),
    userStoryScore: new FormControl(null, [Validators.required]),
    fechaMaxima: new FormControl(null, [Validators.required])
  });

  userStory: any;
  id: any;
  userStoryState: any;


  constructor(
    private userStoryService: userStoryService,
    private route: ActivatedRoute,
    private userStoryStateService: UserStoyStatusService,
    private route1:Router,
    private dialogRef: MatDialogRef<UserStoryComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    ){
  }

  ngOnInit(): void {
    this.id = this.data.userStoryId;
    this.getUserStoryById(this.id)
    this.getUserStoryState()
  }

  getUserStoryState(){
    this.userStoryStateService.getAlluser_story_status().subscribe(resp =>{
      this.userStoryState = resp;
    })
  }

  getUserStoryById(id: string | null) {
    this.userStoryService.getUserStoryById(id).subscribe(resp => {
        this.userStory = resp;
        this.userStoryForm.patchValue({
          userStoryName: this.userStory.userStoryName,
          userStoryStateId: this.userStory.userStoryStateId,
          userStoryScore: this.userStory.userStoryScore,
          fechaMaxima: this.userStory.fechaMaxima,
        });
      },
    );
  }

  edit() {
    if (this.userStoryForm.valid) {
      const data = {
        userStoryArchive: this.userStoryForm.get('userStoryArchive')?.value,
        userStoryName: this.userStoryForm.get('userStoryName')?.value,
        userStoryStateId: this.userStoryForm.get('userStoryStateId')?.value,
        userStoryScore: this.userStoryForm.get('userStoryScore')?.value,
        fechaMaxima: this.userStoryForm.get('fechaMaxima')?.value
      }
      this.userStoryService.updateUserStory(this.id, data).subscribe(

        (resp) => {
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Historia de Usuario editada',
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
        },
        );
        this.userStoryForm.reset();
        this.dialogRef.close();
    }

  }


}
