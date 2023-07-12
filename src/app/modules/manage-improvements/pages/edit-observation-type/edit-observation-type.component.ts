import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ImprovementsService } from '../service/improvements.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-observation-type',
  templateUrl: './edit-observation-type.component.html',
  styleUrls: ['./edit-observation-type.component.scss']
})
export class EditObservationTypeComponent {

  observationId: String= '';
  observationName: String='';

  editTypeObservationsForm: FormGroup= new FormGroup({
    observationType: new FormControl(null, [Validators.required])
  })

  constructor(
    private dialogRef : MatDialogRef<EditObservationTypeComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public improvementService: ImprovementsService
   
  ){}

  ngOnInit(){
    this.observationId= this.data.observationId;
    this.observationName= this.data.observationName;
    console.log(this.observationId)
    this.editTypeObservationsForm.patchValue({
      observationType: this.observationName
    })
  }


  public editObservationType(){

    if(this.editTypeObservationsForm.valid){
      console.log("Entra")
      const obsTypeDta={
        observationName: this.editTypeObservationsForm.get('observationType')?.value
      }

      this.improvementService.editObservationType(this.observationId, obsTypeDta).subscribe({
        next: (resp)=>{
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Tipo de obervacion agregado correctamente',
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
          this.editTypeObservationsForm.reset();
          this.dialogRef.close();
        }
        , 
        error: (err)=>{}
      })
    }
  }

}
