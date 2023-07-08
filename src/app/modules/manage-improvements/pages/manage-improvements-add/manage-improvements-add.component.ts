import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import { AreaInterface } from 'src/app/modules/area/pages/Interface/interface-area';
import { AreaService } from 'src/app/modules/area/pages/service/area.service';
import { userStoryService } from 'src/app/modules/userStory/pages/service/user-story.service';
import { ImprovementsService } from '../service/improvements.service';
import { MatDialogRef } from '@angular/material/dialog';
import { ManageImprovementsComponent } from '../manage-improvements/manage-improvements.component';
import Swal from 'sweetalert2';




@Component({
  selector: 'app-manage-improvements-add',
  templateUrl: './manage-improvements-add.component.html',
  styleUrls: ['./manage-improvements-add.component.scss'],
})
export class ManageImprovementsAddComponent implements OnInit{

  improvementsForm: FormGroup = new FormGroup({
    areaId: new FormControl(null, [Validators.required]),
    teamId: new FormControl(null, [Validators.required]),
    userStoryId: new FormControl(null, [Validators.required]),
    taskId: new FormControl(null, [Validators.required]),
    observationId: new FormControl(null, [Validators.required]),
    observationn: new FormControl(null, [Validators.required, Validators.maxLength(200)])
  });

  areaId: any;
  teamId: any;
  teams: any;
  task: any;
  userStoreId: any;
  userStorys: any;
  observations: any;
  areas : AreaInterface[]= [];
  improvements: any;

  constructor(
    private userStoryService: userStoryService,
    private areaService : AreaService,
    private improvementsService:ImprovementsService,
    private dialogRef : MatDialogRef<ManageImprovementsComponent>
  ) {
  }

  ngOnInit(): void {

    this.areaService.getAllArea().subscribe((data) => {
      this.areas = data;
    },
  );
  this.getAllTask();
  this.getAllObservations();
  }

  selectArea(){
    this.areaId = this.improvementsForm.get('areaId')?.value;
    this.improvementsService.getTeamArea(this.areaId).subscribe( resp => {
      this.teams = resp;
    })
  }

  selectTeam(){
    this.teamId = this.improvementsForm.get('teamId')?.value;
    this.userStoryService.getUserStoryToTeam(this.teamId).subscribe(resp => {
      this.userStorys = resp;

    })
  }

  getAllTask(){
    this.improvementsService.GetAllTask().subscribe(resp => {
      this.task = resp;
    })
  }

  getAllObservations(){
    this.improvementsService.GetAllObservations().subscribe(resp => {
      this.observations = resp;
    })
  }

  getAllImprovements(){
    this.improvementsService.getAllImprovements().subscribe(resp => {
      this.improvements = resp;
    })
  }

  saveImprovements(): void {
    if(this.improvementsForm.valid) {
      const improvements = {
        areaId: this.improvementsForm.get('areaId')?.value,
        teamId: this.improvementsForm.get('teamId')?.value,
        userStoryId: this.improvementsForm.get('userStoryId')?.value,
        taskId: this.improvementsForm.get('taskId')?.value,
        observationId: this.improvementsForm.get('observationId')?.value,
        observationn: this.improvementsForm.get('observationn')?.value,
      }
      this.improvementsService.saveImprovements(improvements).subscribe((resp=>{
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Mejora Guardada',
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

        this.dialogRef.close();
        this.improvementsForm.reset();
        this.getAllImprovements();

      }))

    }
  }
  CloseModal(): void {
    this.dialogRef.close();


  }
}
