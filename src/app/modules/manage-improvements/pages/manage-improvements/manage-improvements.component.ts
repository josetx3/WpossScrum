import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { ImprovementsService } from '../service/improvements.service';
import { ManageImprovements } from '../interface/improvements';
import { ManageImprovementsAddComponent } from '../manage-improvements-add/manage-improvements-add.component';
import { ManageImprovementsSeeComponent } from '../manage-improvements-see/manage-improvements-see.component';

@Component({
  selector: 'app-manage-improvements',
  templateUrl: './manage-improvements.component.html',
  styleUrls: ['./manage-improvements.component.scss'],
})
export class ManageImprovementsComponent implements OnInit {
  improvements: ManageImprovements[] = [];
  constructor(
    private dialog: MatDialog,
    private improvementsService: ImprovementsService
  ) {}

  ngOnInit(): void {
    this.getAllImprovements();
  }

  getAllImprovements() {
    this.improvementsService.getAllImprovements().subscribe((resp) => {
      this.improvements = resp;
    });
  }

  AddImprovementsModal(): void {
    const dialogRef = this.dialog.open(ManageImprovementsAddComponent, {
      width: '500px',
      maxHeight: '600px',
    });
    dialogRef.afterClosed().subscribe({
      next: (resp) => {
        this.getAllImprovements();
      },
    });
  }

  viewMejora(id: string): void {
    const dialogRef = this.dialog.open(ManageImprovementsSeeComponent, {
      width: '400px',
      maxHeight: '600px',
      data: { improvementsId: id },
    });
    dialogRef.afterClosed().subscribe({
      next: (resp) => {
        this.getAllImprovements();
      },
    });
  }

  deleteImprovements(id: string) {
    Swal.fire({
      title: 'Desea eliminar esta mejora?',
      text: ' La información eliminada no se puede recuperar',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'si, eliminar!',
    }).then((result) => {
      if (result.isConfirmed) {
        this.improvementsService.deleteImprovements(id).subscribe({
          next: (resp) => {
            Swal.fire({
              position: 'top-end',
              icon: 'success',
              title: 'Mejora Eliminada',
              showConfirmButton: false,
              timer: 1500,
              toast: true,
              customClass: {
                container: 'my-swal-container',
                title: 'my-swal-title',
                icon: 'my-swal-icon',
              },
              background: '#c6edb9',
            });
            this.getAllImprovements();
          },
        });
      }
    });
  }
}
