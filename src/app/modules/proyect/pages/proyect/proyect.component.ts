import { Component, OnInit } from '@angular/core';
import { ProjectService } from '../service/project.service';
import { AreaService } from 'src/app/modules/area/pages/service/area.service';
import { MatDialog } from '@angular/material/dialog';
import { Proyect } from '../interface/proyect';
import { ProyectAddComponent } from '../proyect-add/proyect-add.component';
import { ProyectEditComponent } from '../proyect-edit/proyect-edit.component';
import { CustomerService } from 'src/app/modules/customer/pages/service/customer.service';
import { ProyectSubprojectComponent } from '../proyect-subproject/proyect-subproject.component';

@Component({
  selector: 'app-proyect',
  templateUrl: './proyect.component.html',
  styleUrls: ['./proyect.component.scss'],
})
export class ProyectComponent implements OnInit {
  proyect: Proyect[] = [];
  projectId: string = '';

  constructor(
    private proyectService: ProjectService,
    private customerService: CustomerService,
    private areaService: AreaService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getAllProject();
  }

  getAllProject(): void {
    this.proyectService.getAllProyect().subscribe((resp) => {
      resp.forEach(
        (item: { clientId: string | null; areaId: string | null }) => {
          this.customerService
            .getCustomerById(item.clientId)
            .forEach((customer) => {
              item.clientId = customer.client_name;
            });

          this.areaService.getArea(item.areaId).forEach((area) => {
            item.areaId = area.areaName;
          });
        }
      );
      this.proyect = resp;

    });
  }

  addProjetModal(): void {
    const dialogRef = this.dialog.open(ProyectAddComponent, {});

    dialogRef.afterClosed().subscribe((resul) => {
      this.getAllProject();
    });
  }

  editProjetModal(proyectId: number) {
    const dialogRef = this.dialog.open(ProyectEditComponent, {
      data: { projectId: proyectId },
    });
    dialogRef.afterClosed().subscribe((resul) => {
      this.getAllProject();
    });
  }

  viewSubprojectsToProyect(projectId: number) {
    const dialogRef = this.dialog.open(ProyectSubprojectComponent,{
      data: {projectId: projectId}
    });
    dialogRef.afterClosed().subscribe((resul) => {
      this.getAllProject();
    });
  }

}
