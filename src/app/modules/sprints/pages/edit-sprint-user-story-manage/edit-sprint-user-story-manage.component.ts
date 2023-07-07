import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { SprintsService } from '../service/sprints.service';

@Component({
  selector: 'app-edit-sprint-user-story-manage',
  templateUrl: './edit-sprint-user-story-manage.component.html',
  styleUrls: ['./edit-sprint-user-story-manage.component.scss']
})
export class EditSprintUserStoryManageComponent implements OnInit{
  // idSprint: idSprint, userStoryId: userStoryId, pointsTo:this.pointsTot
  idSprint: String | null= '';
  userStoryId: String | null='';
  pointsTot:number=0;
  points: number=0;
  scorePointSprint: number=0;

  editPointForm: FormGroup = new FormGroup({
    pointsHU: new FormControl(null, [Validators.required])
  })

  constructor(
    public dialogRef: MatDialogRef<EditSprintUserStoryManageComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public sprintService: SprintsService
  ){}

  ngOnInit(): void {
    this.idSprint= this.data.idSprint;
    this.userStoryId= this.data.userStoryId;
    this.pointsTot= this.data.pointsTot;
    this.points=this.data.points;
    this.scorePointSprint= this.data.scorePointSprint;
    console.log('scorepoint'+ this.scorePointSprint)
    console.log('El id del sprint es'+this.idSprint+ 'El id del UserStory es'+this.userStoryId+ 'Los puntos totales son'+this.pointsTot)
    this.editPointForm.patchValue({
      pointsHU: this.points   
    })
  }
 
  editPonitHUSprint(){
    if (this.editPointForm.valid) {
        let pointForm= parseInt(this.editPointForm.get('pointsHU')?.value);
        let editPoints= pointForm- this.points;
        let newPoints= this.pointsTot+ editPoints ;
        if(this.points>pointForm){

        }
        if(pointForm === 0){
            console.log('eliminar del sprintuserstory puntos dejando su estado en refinado')
            //actualizar puntos normalmente
        }
        else if(newPoints < this.scorePointSprint)
        {
          console.log('actualizar puntos dejando su estado en desarrollo')
            //
        }else{
          console.log('puntos mayores que el sprint ideal')
        }
    }
  }

}
