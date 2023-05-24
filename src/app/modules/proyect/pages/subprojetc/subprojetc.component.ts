import { Component, OnInit } from '@angular/core';
import { Subproject } from '../interface/subprojects';
import { SubprojectService } from '../service/subproject.service';
import { MatDialog } from '@angular/material/dialog';
import { SubprojetcAddComponent } from '../subprojetc-add/subprojetc-add.component';
import { SubprojetcEditComponent } from '../subprojetc-edit/subprojetc-edit.component';
import { UserStoryAddComponent } from 'src/app/modules/userStory/pages/user-story-add/user-story-add.component';


@Component({
  selector: 'app-subprojetc',
  templateUrl: './subprojetc.component.html',
  styleUrls: ['./subprojetc.component.css']
})
export class SubprojetcComponent implements OnInit {
  subproject: Subproject | any;

  constructor(

    private subprojectService: SubprojectService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.getAllSubproject();
  }
  getAllSubproject():void{
    this.subprojectService.getAllSubprojects().subscribe(resp => {
      this.subproject = resp;
    });
  }

addSubprojetModal(): void {
  const dialogRef = this.dialog.open(SubprojetcAddComponent, {width: '500px'});

  dialogRef.afterClosed().subscribe(resul => {
    this.getAllSubproject();
  })
}

editSubProjetModal(subProyectId: number) {

  const dialogRef = this.dialog.open(SubprojetcEditComponent, {width: '500px',    data:{subProjectId: subProyectId }});

   dialogRef.afterClosed().subscribe(resul => {
    this.getAllSubproject();
   })
}
addUserStoryModal(): void {
  const dialogRef = this.dialog.open(UserStoryAddComponent, {width: '500px', maxHeight: '600px' });

  dialogRef.afterClosed().subscribe(resul =>  {

  })
}

}
