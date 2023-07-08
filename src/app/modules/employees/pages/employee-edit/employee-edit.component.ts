import {Component, OnInit, Inject} from '@angular/core';
import { FormControl, FormGroup, Validators} from "@angular/forms";
import { EmployeesService } from '../service/employees.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { EmployeeComponent } from '../employee/employee.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrls: ['./employee-edit.component.scss']
})
export class EmployeeEditComponent implements OnInit{

  employeeEditForm: FormGroup = new FormGroup({
    employeeName: new FormControl(null, [Validators.required, Validators.maxLength(20)]),
    employeeCharge: new FormControl(null, [Validators.required]),
    employeeEmail: new FormControl(null, [Validators.required, Validators.email]),
    employeeKnowledge: new FormControl(null, [Validators.required]),
    employeeId: new FormControl(),

  })

  employee: any;
  employeePassword: any;
  id: any;


  constructor(
    private employeesService: EmployeesService,
    private route: ActivatedRoute,
    private routeurl: Router,
    private dialogRef: MatDialogRef<EmployeeComponent>,
    @Inject (MAT_DIALOG_DATA) public data: any,
  ) {
  }

  ngOnInit(): void {

    this.id = this.data.employeeId;
    this.getEmployeeById(this.id)
  }

  getEmployeeById(id: string | null) {
    this.employeesService.getEmployeeById(id).subscribe(resp => {
      this.employee = resp;
      this.employeePassword= resp.employeePassword;
      this.employeeEditForm.patchValue({
        employeeName: resp.employeeName,
        employeeCharge: resp.employeeCharge,
        employeeEmail: resp.employeeEmail,
        employeeKnowledge: resp.employeeKnowledge,

      })
    })
  }


  editEmployee() {
    if (this.employeeEditForm.valid) {
      const data = {
        employeeName: this.employeeEditForm.get('employeeName')?.value,
        employeeCharge: this.employeeEditForm.get('employeeCharge')?.value,
        employeeEmail: this.employeeEditForm.get('employeeEmail')?.value,
        employeePassword:this.employeePassword,
        employeeKnowledge: this.employeeEditForm.get('employeeKnowledge')?.value,
      }

      this.employeesService.updateEmployee(this.id, data).subscribe((res) =>{
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Empleado editado con exito',
          showConfirmButton: false,
          timer: 1500,
          toast: true,
          customClass: {
            container: 'my-swal-container',
            title: 'my-swal-title',
            icon: 'my-swal-icon',
            popup: 'my-swal-popup',
          },
        }),
        this.employeeEditForm.reset();
        this.dialogRef.close();
      })

    }

  }


}
