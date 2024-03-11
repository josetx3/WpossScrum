import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SprintsService } from '../service/sprints.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { MatDialog } from '@angular/material/dialog';
import { EditSprintUserStoryManageComponent } from '../edit-sprint-user-story-manage/edit-sprint-user-story-manage.component';


@Component({
  selector: 'app-sprint-user-story-manage',
  templateUrl: './sprint-user-story-manage.component.html',
  styleUrls: ['./sprint-user-story-manage.component.scss']
})
export class SprintUserStoryManageComponent {

  sprintId: String | null='';
  // SprintDate: SprintsDate[]=[];
  SprintDate: any;
  userStorys: any;
  userStorysDes: any;
  areaId: String='';
  teamId: String='';
  pointsTot: number=0;
  scorePointSprint: Number=0;

  addUserStoryForm: FormGroup = new FormGroup({
    userStoryId: new FormControl(null, [Validators.required]),
    points: new FormControl(null, [Validators.required])
  })

  constructor(
    private route: ActivatedRoute,
    public sprintService: SprintsService,
    private dialog: MatDialog
    ) { }

  ngOnInit() {
    this.sprintId = this.route.snapshot.paramMap.get('sprintId');
    this.getSprintDateById();
    this.getUseStoryDes();
  }

  getSprintDateById() {
    this.sprintService.getSprintDateById(this.sprintId).subscribe({
      next: (resp) => {
        this.SprintDate = resp;
        this.areaId= this.SprintDate.areaId;
        this.teamId= this.SprintDate.teamId;
        this.scorePointSprint= this.SprintDate.ScorePointSprint;
        this.getUseStoryRef();

      }
      ,
      error: (err)=>{
      }
    }

      );
  }

  getUseStoryRef(){ //trae hu ref segun el team y el area
    this.sprintService.getUseStoryRef(this.teamId, this.areaId).subscribe((data)=>{
       this.userStorys=data;
  });
  }

  getUseStoryDes(){ //trae hu des segun el team y el area
    this.sprintService.getUseStoryDes(this.sprintId).subscribe(
      {
        next: (data)=>{
          this.userStorysDes=data;
          this.pointsTot=0;
          this.userStorysDes.forEach((obj: { points: number; })=>{
             this.pointsTot+=obj.points;
          })
         }
        ,
        error: (err)=>{
          Swal.fire({
            position: 'top-end',
            icon: 'warning',
            title: 'No hay HU en estado de desarrollo',
            showConfirmButton: false,
            timer: 3000,
            toast: true,
            customClass: {
              container: 'my-swal-container',
              title: 'my-swal-title',
              icon: 'my-swal-icon',
            },
            background: '#FFFEFB',
          })
        }
      }
      );
  }

  addUserStoryToSprint(){
    if (this.addUserStoryForm.valid) {
      let pointHu=parseInt(this.addUserStoryForm.get('points')?.value);
      this.pointsTot+=pointHu;
      if(this.pointsTot<=this.SprintDate.ScorePointSprint){
        const dataSprintUserStory =
        {
          idSprint:this.sprintId,
          userStoryId:this.addUserStoryForm.get('userStoryId')?.value,
          points: this.addUserStoryForm.get('points')?.value
        }
        console.log(dataSprintUserStory)
        this.sprintService.addUserStoryToSprint(dataSprintUserStory).subscribe({
          next:
          (resp)=>{
            Swal.fire({
              position: 'top-end',
              icon: 'success',
              title: 'Historia de usuario añadido al sprint exitosamente',
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
          this.ngOnInit();
          this.addUserStoryForm.reset();
          },
          error:
            err=>{
              err.status({400:
                Swal.fire({
                  position: 'top-end',
                  icon: 'warning',
                  title: 'Error, numero de puntos no validos',
                  showConfirmButton: false,
                  timer: 1500,
                  toast: true,
                  customClass: {
                    container: 'my-swal-container',
                    title: 'my-swal-title',
                    icon: 'my-swal-icon',
                  },
                  background: '#FFFEFB',
                })
              })
              this.ngOnInit();
            }
        })
      }else{
        this.pointsTot=this.pointsTot-pointHu;
        Swal.fire({
          position: 'top-end',
          icon: 'warning',
          title: 'No se puede agregar la Historia de Usuario, número de puntos Sprint superados',
          showConfirmButton: false,
          timer: 3000,
          toast: true,
          customClass: {
            container: 'my-swal-container',
            title: 'my-swal-title',
            icon: 'my-swal-icon',
          },
          background: '#FFFEFB',
        })
        this.ngOnInit();
      }
    }

  }

  editSprintUserStoryManageModal(idSprint: String, userStoryId:String, points: Number) {
    const dialogRef = this.dialog.open(EditSprintUserStoryManageComponent,

    {width: '500px', maxHeight: '600px',
    data:{
        idSprint: idSprint,
        userStoryId: userStoryId,
        points:points,
        scorePointSprint: this.scorePointSprint,
        pointsTot:this.pointsTot }});
     dialogRef.afterClosed().subscribe(resul => {
      this.getUseStoryDes();
     })
  }
}
