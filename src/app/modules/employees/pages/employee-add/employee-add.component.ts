import { Component,OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators} from "@angular/forms";
import { Employee } from '../interface/employee';
import { EmployeesService } from '../service/employees.service';
import { MatDialogRef } from '@angular/material/dialog';
import { EmployeeComponent } from '../employee/employee.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-employee-add',
  templateUrl: './employee-add.component.html',
  styleUrls: ['./employee-add.component.scss']
})
export class EmployeeAddComponent implements OnInit{


  employeeForm: FormGroup = new FormGroup({
    employeeName: new FormControl(null, [Validators.required, Validators.maxLength(20)]),
    employeeCharge: new FormControl(null, [Validators.required]),
    employeeEmail: new FormControl(null, [Validators.required, Validators.email]),
    employeeKnowledge: new FormControl(null, [Validators.required]),
    employeePassword: new FormControl(null, [Validators.required]),
    employeeId: new FormControl()
  });
  employee: Employee | any;

  constructor(
    private employeesService: EmployeesService,
    private dialogRef : MatDialogRef<EmployeeComponent>,
  ) {

  }

  ngOnInit(): void {
  }



  saveEmployee() {
    if(this.employeeForm.valid) {
      const data = {
        employeeName: this.employeeForm.get('employeeName')?.value,
        employeeCharge: this.employeeForm.get('employeeCharge')?.value,
        employeeEmail: this.employeeForm.get('employeeEmail')?.value,
        employeeKnowledge: this.employeeForm.get('employeeKnowledge')?.value,
        employeePassword: this.employeeForm.get('employeePassword')?.value
      }
      this.employeesService.saveEmployee(data).subscribe(
        (resp => {
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Empleado creado',
            showConfirmButton: false,
            timer: 1500,
            toast: true,
            customClass: {
              container: 'my-swal-container',
              title: 'my-swal-title',
              icon: 'my-swal-icon',
            },
            background: '#E6F4EA',
          })
        this.employeeForm.reset();
        this.dialogRef.close();
      }))
    }
  }


}
