import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserStoyStatusService } from '../service/user-stoy-status.service';
import Swal from 'sweetalert2';
import { UserStoryState } from '../interface/UserStoryState';

@Component({
  selector: 'app-state-user-story-add',
  templateUrl: './state-user-story-add.component.html',
  styleUrls: ['./state-user-story-add.component.scss']
})
export class StateUserStoryAddComponent {
  userStoryState: any='';


  constructor(
    public userStoryStausService: UserStoyStatusService
  ){}

  ngOnInit(){
    this.getStateUserStory()
  }

  addEstateHUForm: FormGroup= new FormGroup({
    estateUS: new FormControl(null, [Validators.required])
  })

  public addEstateHU(){

    const dataEstateHU={
      userStoryStateName: this.addEstateHUForm.get('estateUS')?.value
    }
      this.userStoryStausService.addEstateHU(dataEstateHU).subscribe({
        next: (resp)=>{
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Estado de historia de usuario agregado correctamente',
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
          this.getStateUserStory()
        }
        ,
        error: (err)=>{
          Swal.fire({
            position: 'top-end',
            icon: 'warning',
            title: 'El estado que intenta agregar ya se encuentra añadido.',
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

  public getStateUserStory(){
    this.userStoryStausService.getAlluser_story_status().subscribe({
      next: (res)=>{
          this.userStoryState= res;
      }
      ,
      error: (err)=>{}
    })
  }
}
