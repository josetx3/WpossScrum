import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AreaEditComponent } from '../area-edit/area-edit.component';
import { AreaInterface } from '../Interface/interface-area';
import { AreaService } from '../service/area.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-area',
  templateUrl: './area.component.html',
  styleUrls: ['./area.component.scss'],
})
export class AreaComponent implements OnInit {
  areaForm: FormGroup = new FormGroup({
    areaId: new FormControl(null),
    areaName: new FormControl(null, [Validators.required,Validators.min(0),Validators.maxLength(20)])
  });

  area: AreaInterface[] = [];
  areaId: string = '';

  constructor(private areaService: AreaService, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.getAllAreas();
  }

  getAllAreas() {
    this.areaService.getAllArea().subscribe((resp) => {
      this.area = resp;
    });
  } //getAllAreas()

  saveArea(): void {
    if (this.areaForm.valid) {
      const data = {
        areaId: this.areaForm.get('areaId')?.value,
        areaName: this.areaForm.get('areaName')?.value,
      };

      this.areaService.saveArea(data).subscribe(() => {
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Area guardada',
          showConfirmButton: false,
          timer: 1500,
          toast: true,
          customClass: {
            container: 'my-swal-container',
            title: 'my-swal-title',
            icon: 'my-swal-icon',
          },
          background: '#E6F4EA',
        });
        this.areaForm.reset();
        this.getAllAreas();
      });
    } else {
    }
  } //saveArea()

  deleteArea(id: string) {
    Swal.fire({
      title: 'Desea eliminar esta area?',
      text: 'Debe verificar que esta area no esté relacionada con algún proyecto. La información eliminada no se puede recuperar',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#007bff',
      cancelButtonColor: '#1ABC9C',
      confirmButtonText: 'si, eliminar!',
    }).then((result) => {
      if (result.isConfirmed) {
        this.areaService.deleteArea(id).subscribe({
          next:
          (resp) => {
          this.getAllAreas();
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Area eliminada',
            showConfirmButton: false,
            timer: 1500,
            toast: true,
            customClass: {
              container: 'my-swal-container',
              title: 'my-swal-title',
              icon: 'my-swal-icon',
              popup: 'my-swal-popup',
            },
            background: '#FFFEFB',
          });
        },
        error:(err)=>{
          Swal.fire({
            position: 'top-end',
            icon: 'warning',
            title: 'Esta area no se puede eliminar ya que tiene proyectos asociados a ella.',
            showConfirmButton: false,
            timer: 3000,
            toast: true,
            customClass: {
              container: 'my-swal-container',
              title: 'my-swal-title',
              icon: 'my-swal-icon',
              popup: 'my-swal-popup',
            },
            background: '#FFFEFB',
          })
        }
      });
      } 
    });
  } // deleteArea

  editAreaModal(areaId: string) {
    const dialogRef = this.dialog.open(AreaEditComponent, {
      width: '500px',
      height: '275px',
      data: { areaId: areaId },
    });
    dialogRef.afterClosed().subscribe((resul) => {
      this.getAllAreas();
    });
  } //editAreaModal
}
