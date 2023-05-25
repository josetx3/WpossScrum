import { Component, OnInit } from '@angular/core';
import { TeamsService } from '../service/teams.service';
import { ActivatedRoute } from '@angular/router';
import { EmployeesService } from 'src/app/modules/employees/pages/service/employees.service';

@Component({
  selector: 'app-team-add-employee',
  templateUrl: './team-add-employee.component.html',
  styleUrls: ['./team-add-employee.component.scss']
})
export class TeamAddEmployeeComponent implements OnInit {


  teamId: string | null = '';
  teamEmployee: string[] = [];

  employees: any;
  employeesAddTeam:any;


  constructor(
    private employeesService: EmployeesService,
    private teamService: TeamsService,
    private route: ActivatedRoute,
  ) {
  }

  ngOnInit() {

    this.teamId = this.route.snapshot.paramMap.get('teamId');

    // this.getAllEmployeesAddToTeam(this.teamId);

    this.getAllEmployees();

  }


  getAllEmployees() {
    this.employeesService.getAllEmployee().subscribe(
      resp => {
      this.employees = resp;
      this.employees.map((r: { checked: boolean; }) => {
        r.checked = false;
      })
    })

  }


  AddEmployeeTeam() {
    let employeesAdd = this.employees.filter((resp: { checked: boolean; }) => resp.checked === true);
    employeesAdd.forEach((element: { employeeId: any; }) => {
      let item = element.employeeId


      this.teamEmployee.push(item);


      this.employees = this.employees.filter((resp: { checked: boolean; }) => resp.checked === false)
      console.log(this.teamEmployee);

    });
    this.teamService.addEmployeeTeam(this.teamId, this.teamEmployee).subscribe(resp=>{
       this.getAllEmployeesAddToTeam(this.teamId);
    })
  }


  getAllEmployeesAddToTeam(teamId: string | null){
    this.employeesService.getEmployeesAddToTeam(teamId).subscribe(resp=>{ //empleados que pertenecen a un equipo
      this.employeesAddTeam=resp;
    })
  }

}
