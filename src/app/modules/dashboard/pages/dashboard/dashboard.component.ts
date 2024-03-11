import { Component, OnInit } from '@angular/core';
import { AreaService } from 'src/app/modules/area/pages/service/area.service';
import { CustomerService } from 'src/app/modules/customer/pages/service/customer.service';
import { ProjectService } from 'src/app/modules/proyect/pages/service/project.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  project: any;

  constructor(
        private projectService: ProjectService,
    private areaService: AreaService,
    private customerService: CustomerService,
  ){}

   ngOnInit(): void {
    this.projectService.getAllProyect().subscribe(resp => {
      resp.forEach((item: { clientId: string | null; areaId: string | null; }) => {
        this.customerService.getCustomerById(item.clientId).forEach(customer => {
          item.clientId = customer.client_name;
        })


        this.areaService.getArea(item.areaId).forEach(area => {
          item.areaId = area.areaName
        })
      })
      this.project = resp;
    });
  }

}
