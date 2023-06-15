import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ImprovementsService } from '../service/improvements.service';
import { ActivatedRoute } from '@angular/router';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-manage-improvements-see',
  templateUrl: './manage-improvements-see.component.html',
  styleUrls: ['./manage-improvements-see.component.scss'],
})
export class ManageImprovementsSeeComponent implements OnInit {
  improvementsForm: FormGroup = new FormGroup({
    areaName: new FormControl({ value: null, disabled: true }),
    teamName: new FormControl({ value: null, disabled: true }),
    userStoryName: new FormControl({ value: null, disabled: true }),
    nameTask: new FormControl({ value: null, disabled: true }),
    observationName: new FormControl({ value: null, disabled: true }),
    observationn: new FormControl({ value: null, disabled: true }),
  });

  improvements: any;
  improvementsId: any;

  constructor(
    private improvementsService: ImprovementsService,
    private route: ActivatedRoute,
    @Inject (MAT_DIALOG_DATA) public data: any,
  ) {}

  ngOnInit(): void {
    this.improvementsId = this.data.improvementsId;

    this.getImprovementsById(this.improvementsId);
  }

  getImprovementsById(id: string) {
    this.improvementsService.getImprovementsById(id).subscribe((resp) => {
      this.improvements = resp;
      this.improvementsForm.patchValue({
        areaName: this.improvements.areaName,
        teamName: this.improvements.teamName,
        userStoryName: this.improvements.userStoryName,
        nameTask: this.improvements.nameTask,
        observationName: this.improvements.observationName,
        observationn: this.improvements.observationn,
      });
    });
  }
}
