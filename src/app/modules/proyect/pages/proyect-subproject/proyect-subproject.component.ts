import { Component, Inject, OnInit } from '@angular/core';
import {  SubprojectProyect } from '../interface/subprojects';
import { SubprojectService } from '../service/subproject.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ProyectComponent } from '../proyect/proyect.component';
import { Router } from '@angular/router';
import {AreaModule} from "../../../area/area.module";

@Component({
  selector: 'app-proyect-subproject',
  templateUrl: './proyect-subproject.component.html',
  styleUrls: ['./proyect-subproject.component.scss'],
})
export class ProyectSubprojectComponent implements OnInit {
  subproject: SubprojectProyect[]=[];
  projectId: string='';

  constructor(
    private subprojectService: SubprojectService,
    private dialog: MatDialogRef<ProyectComponent>,
    private route: Router,
    @Inject(MAT_DIALOG_DATA) public data:any,
    private dialogRef: MatDialogRef<AreaModule>
  ) {}

  ngOnInit(): void {
    this.projectId = this.data.projectId;
    this.getSubprojectToProyect(this.projectId);
  }

  getSubprojectToProyect(projectId: string) {
    this.subprojectService.getSubprojectToProyect(projectId).subscribe({
      next: (resp) => {
          this.subproject = resp
      },
    });
  }

  toSubproject(){
    this.route.navigate(['app/proyect/subproject'])
    this.dialogRef.close();
  }

  protected readonly JSON = JSON;
}
