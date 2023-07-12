import { Component, OnInit } from '@angular/core';
import { EmployeesService } from '../service/employees.service';
import { Employee } from '../interface/employee';
import { MatDialog } from '@angular/material/dialog';
import { EmployeeAddComponent } from '../employee-add/employee-add.component';
import { EmployeeEditComponent } from '../employee-edit/employee-edit.component';
import Swal from 'sweetalert2';
import { EmployeTeamsComponent } from '../employe-teams/employe-teams.component';

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

  deleteEmployee(employeeId: String){
    Swal.fire({
      title: '¿Está seguro que quiere eliminar al empleado?',
      text: ' Antes de eliminar el empleado, debe verificar que el empleado no se encuentre asociado a un área o equipo. Esta acción no es reversible.',
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#007bff',
      cancelButtonColor: '#F1948A',
      confirmButtonText: 'Sí eliminar!',
      customClass: {
        container: 'my-swal-container',
        title: 'my-swal-title',
        icon: 'my-swal-icon',
      },
      background: '#FFFEFB'
    }).then((result) => {
      if (result.isConfirmed) {
        this.employeesService.deleteEmployee(employeeId).subscribe({
          next: (resp)=>{
            Swal.fire({
              position: 'top-end',
              title: 'Empleado eliminado sastisfactoriamente',
              icon: 'success',
              toast: true,
              showConfirmButton: false,
              timer: 3000,
              customClass: {
                container: 'my-swal-container',
                title: 'my-swal-title',
                icon: 'my-swal-icon',
              },
              background: '#E6F4EA'
            })
            this.getAllEmployee();
          }
          ,
          error:(err)=>{
            Swal.fire({
              position: 'top-end',
              icon: 'warning',
              title: 'No se pudo eliminar el empleado',
              text:'Debe eliminarlo primero de su equipo y demás relaciones.',
              showConfirmButton: false,
              timer: 3000,
              toast: true,
              customClass: {
                container: 'my-swal-container',
                title: 'my-swal-title',
                icon: 'my-swal-icon',
              },
              background: '#FFFEFB'
            })
          }
        })
       }
    })
  }

  viewTeams(employeeId: String){
    const matDialog= this.dialog.open(EmployeTeamsComponent, {width:'500px', data:{ employeeId: employeeId}} )
  }


}
