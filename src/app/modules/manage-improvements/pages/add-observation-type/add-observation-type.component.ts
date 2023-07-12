import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ImprovementsService } from '../service/improvements.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-observation-type',
  templateUrl: './add-observation-type.component.html',
  styleUrls: ['./add-observation-type.component.scss']
})
export class AddObservationTypeComponent {

  typeObservation: any='';

  addTypeObservationsForm: FormGroup= new FormGroup({
    observationType: new FormControl(null, [Validators.required])
  })

  constructor(
    public improvementsService: ImprovementsService
  ){
  }

  ngOnInit(){
    this.getTypeObservations();
  }

  addObservationType(){

    const obsTypeDta={
      observationName: this.addTypeObservationsForm.get('observationType')?.value
    }

    this.improvementsService.addObservationType(obsTypeDta).subscribe({
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
          this.getTypeObservations();
        }
        ,
        error: (err)=>{
          Swal.fire({
            position: 'top-end',
            icon: 'warning',
            title: 'Tipo de observacion ya se encuentra agregado',
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
        }
      })

  }

  getTypeObservations(){
    this.improvementsService.GetAllObservations().subscribe({
      next: (resp)=>{
          this.typeObservation= resp
      }, 
      error: (err)=>{
        Swal.fire({
          position: 'top-end',
          icon: 'warning',
          title: 'No se han encontrado observaciones',
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
      }
    
    })
  }

  editTypeObservation(){
    
  }
}
