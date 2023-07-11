import { Component, OnInit } from '@angular/core';
import { TeamsService } from '../service/teams.service';
import { ActivatedRoute } from '@angular/router';
import { EmployeesService } from 'src/app/modules/employees/pages/service/employees.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-team-add-employee',
  templateUrl: './team-add-employee.component.html',
  styleUrls: ['./team-add-employee.component.scss'],
})
export class TeamAddEmployeeComponent implements OnInit {
  teamId: string | null = '';
  teamEmployee: string[] = [];

  employees: any;
  employeesAddTeam: any;

  constructor(
    private employeesService: EmployeesService,
    private teamService: TeamsService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.teamId = this.route.snapshot.paramMap.get('teamId');

    this.getAllEmployeesAddToTeam(this.teamId);

    this.getAllnoExistAndTeam(this.teamId);

    //this.getAllEmployees();
  }

  //! Agrega los empleados seleccionados al equipo
  AddEmployeeTeam() {
    let employeesAdd = this.employees.filter(
      (resp: { checked: boolean }) => resp.checked === true
    );

    //* Agrega los empleados seleccionados
    employeesAdd.forEach((element: { employeeId: any }) => {
      let item = element.employeeId;
      this.teamEmployee.push(item);
      this.employees = this.employees.filter(
        (resp: { checked: boolean }) => resp.checked === false
      );
    });

    this.teamService
      .addEmployeeTeam(this.teamId, this.teamEmployee)
      .subscribe(
        (resp) => {
        this.getAllEmployeesAddToTeam(this.teamId);
        this.teamEmployee= [];
      }
    );
  }

  //! Lista los empleados agregados al equipo
  getAllEmployeesAddToTeam(teamId: string | null) {
    this.employeesService.getEmployeesAddToTeam(teamId).subscribe((resp) => {
      //*empleados que pertenecen a un equipo
      this.employeesAddTeam = resp;
    });
  }

  //!lista todos los empleados existentes
  getAllEmployees() {
    this.employeesService.getAllEmployee().subscribe((resp) => {
      this.employees = resp;
      this.employees.map((r: { checked: boolean }) => {
        r.checked = false;
      });
    });
  }

  getAllnoExistAndTeam(teamId: string | null) {
    this.employeesService.getAllnoExistAndTeam(teamId).subscribe((resp) => {
      this.employees = resp;
      this.employees.map((r: { checked: boolean }) => {
        r.checked = false;
      });
    });
  }
}
