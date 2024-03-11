import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Area } from 'src/app/modules/area/pages/Interface/interface-area';
import { ProjectService } from '../service/project.service';
import { Router } from '@angular/router';
import { MatDialogRef } from '@angular/material/dialog';
import { ProyectModule } from '../../proyect.module';
import Swal from 'sweetalert2';
import { Client } from 'src/app/modules/customer/pages/interface/customer';

@Component({
  selector: 'app-proyect-add',
  templateUrl: './proyect-add.component.html',
  styleUrls: ['./proyect-add.component.scss'],
})
export class ProyectAddComponent implements OnInit {

  projectAddForm: FormGroup = new FormGroup({
    projectName: new FormControl(null, [
      Validators.required,
      Validators.maxLength(20),
    ]),
    projectClient: new FormControl(null, [Validators.required]),
    projectArea: new FormControl(null, [Validators.required]),
    archive: new FormControl(null),
  });

  public clients: Client[] = [];
  public areas: Area[] = [];
  public archivos: any = [];
  public base64String: any;

  constructor(
    private projectService: ProjectService,
    private route: Router,
    private dialogRef: MatDialogRef<ProyectModule>
  ) {}

  ngOnInit(): void {
    this.getAllClient();
    this.getAllAreas();
  }

  getAllAreas(): void {
    this.projectService.getArea().subscribe({
      next: (resp) => {
        this.areas = resp;
      },
    });
  }

  getAllClient(): void {
    this.projectService.getClient().subscribe({
      next: (resp) => {
        this.clients = resp;
      },
    });
  }

  saveProject(): void {
    if (this.projectAddForm.valid) {
      const data = {
        projectName: this.projectAddForm.get('projectName')?.value,
        clientId: this.projectAddForm.get('projectClient')?.value,
        areaId: this.projectAddForm.get('projectArea')?.value,
        archive: this.base64String,
      };
      this.projectService.saveProyect(data).subscribe((resp) => {
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Proyecto creado',
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
        this.dialogRef.close();
        this.projectAddForm.reset();
      });
    }
  }


  upload_image(event: any) {
    let archive = event.target.files;
    let reader = new FileReader();

    reader.readAsDataURL(archive[0]);
    reader.onloadend = () => {
      // Obtener la URL de datos codificada en base64 de la imagen cargada
      let imageDataURL = reader.result;
      this.base64String = imageDataURL;
      // Mostrar la imagen en la interfaz de usuario
      let imageElement = document.createElement('img');
      //imageElement.src = imageDataURL;
      document.body.appendChild(imageElement);
    };
  }

}
