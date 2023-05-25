import { Component, OnInit, ViewChild } from '@angular/core';
import { Employee } from '../../employees/pages/interface/employee';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { TeamsService } from '../pages/service/teams.service';
import { EmployeesService } from '../../employees/pages/service/employees.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-team-employees-list',
  templateUrl: './team-employees-list.component.html',
  styleUrls: ['./team-employees-list.component.scss']
})
export class TeamEmployeesListComponent implements OnInit {
  employees: Employee[] = [];
  dataSource: MatTableDataSource<Employee> = new MatTableDataSource();
  displayedColumns: string[] = ['Id', 'Nombre', 'Cargo', 'Conocimiento'];

  @ViewChild(MatPaginator) paginator: MatPaginator | any;
  @ViewChild(MatSort) sort: MatSort | any;

  constructor(private teamsService: TeamsService, private employeeService: EmployeesService, private route: ActivatedRoute) {

  }

  ngOnInit() {

  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
