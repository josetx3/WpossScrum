import { Component,OnInit,Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AreaService } from '../service/area.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AreaComponent } from '../area/area.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-area-edit',
  templateUrl: './area-edit.component.html',
  styleUrls: ['./area-edit.component.scss']
})
export class AreaEditComponent implements OnInit {



  areaEdit: any;
  areaId: string = '';

  areaEditForm: FormGroup = new FormGroup({
    areaName: new FormControl(null, [Validators.required, Validators.maxLength(20)])
  });

  constructor(

    private areaService: AreaService,
    private dialogRef : MatDialogRef<AreaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,

    ) {

  }

  ngOnInit(): void {

    this.areaId = this.data.areaId;
    console.log(this.areaId)
    this.getArea(this.areaId);

  }

  getAllArea(): void {
    this.areaService.getAllArea().subscribe(resp =>{

    })
  }

  getArea(id: string | null) {
    this.areaService.getArea(id).subscribe(resp => {
        this.areaEdit = resp;
        this.areaEditForm.patchValue({
          areaName: this.areaEdit.areaName
        });
      },
    );
  }

  editArea() {
    if (this.areaEditForm.valid) {
      const data = {
        areaName: this.areaEditForm.get('areaName')?.value,
      }

      this.areaService.updateArea(this.areaId, data).subscribe(
        (resp) => {
          Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Area editada',
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
          this.areaEditForm.reset();
          this.getAllArea();
        },
       );
    }
  }

  CloseModal(): void {
    this.dialogRef.close();


  }


}
