import { Component, OnInit } from '@angular/core';
import { EmployeesService } from '../service/employees.service';
import { Employee } from '../interface/employee';
import { MatDialog } from '@angular/material/dialog';
import { EmployeeAddComponent } from '../employee-add/employee-add.component';
import { EmployeeEditComponent } from '../employee-edit/employee-edit.component';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit{
  
  employee: Employee[] = [];
  employeeId: string= '';


  constructor(

    private employeesService: EmployeesService,
    private dialog: MatDialog,
  ) {
  }

  ngOnInit(): void {

    this.getAllEmployee();
  }

 
  getAllEmployee() {

    const token = localStorage.getItem('token');
  
    this.employeesService.getAllEmployee().subscribe(resp => {
      this.employee = resp;

    })
  }

  addEmployeeModal(): void {
    const dialogRef = this.dialog.open(EmployeeAddComponent);

    dialogRef.afterClosed().subscribe(resul =>  {
    this.getAllEmployee();

    })
  }

  editEmployeeModal(employeeId: string): void {
    const dialogRef = this.dialog.open(EmployeeEditComponent, {width: '500px',    data:{employeeId: employeeId }})

    dialogRef.afterClosed().subscribe( {
      next: (resp)=> {
    this.getAllEmployee();
      }
    })
  }


}
