import { Component, OnInit } from '@angular/core';
import { Subproject } from '../interface/subprojects';
import { SubprojectService } from '../service/subproject.service';
import { MatDialog } from '@angular/material/dialog';
import { SubprojetcAddComponent } from '../subprojetc-add/subprojetc-add.component';
import { SubprojetcEditComponent } from '../subprojetc-edit/subprojetc-edit.component';
import { UserStoryAddComponent } from 'src/app/modules/userStory/pages/user-story-add/user-story-add.component';
import {SubprojectUserStoryComponent} from "../subproject-user-story/subproject-user-story.component";

@Component({
  selector: 'app-subprojetc',
  templateUrl: './subprojetc.component.html',
  styleUrls: ['./subprojetc.component.css'],
})
export class SubprojetcComponent implements OnInit {
  subproject: Subproject | any;

  constructor(
    private subprojectService: SubprojectService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getAllSubproject();
  }
  getAllSubproject(): void {
    this.subprojectService.getAllSubprojects().subscribe((resp) => {
      this.subproject = resp;
    });
  }

  addSubprojetModal(): void {
    const dialogRef = this.dialog.open(SubprojetcAddComponent, {});

    dialogRef.afterClosed().subscribe((resul) => {
      this.getAllSubproject();
    });
  }

  editSubProjetModal(subProyectId: number) {
    const dialogRef = this.dialog.open(SubprojetcEditComponent, {
      data: { subProjectId: subProyectId },
    });

    dialogRef.afterClosed().subscribe((resul) => {
      this.getAllSubproject();
    });
  }
  addUserStoryModal(): void {
    const dialogRef = this.dialog.open(UserStoryAddComponent, {});

    dialogRef.afterClosed().subscribe((resul) => {});
  }

  viewSubprojectsUserStory(subProjectId: string){
    const dialogRef = this.dialog.open(SubprojectUserStoryComponent,{
      data: {subProjectId: subProjectId}
    });
    dialogRef.afterClosed().subscribe((resul) => {
      this.getAllSubproject();
    });
  }

}
