import { Component, OnInit } from '@angular/core';
import { EmployeesService } from '../service/employees.service';
import { Employee, Employee2 } from '../interface/employee';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { encrypt } from 'src/app/utils/encrypt';

@Component({
  selector: 'app-employee-profile',
  templateUrl: './employee-profile.component.html',
  styleUrls: ['./employee-profile.component.scss']
})
export class EmployeeProfileComponent implements OnInit{
  myProfile:  Employee2[]=[];
  id: any;


  perfilEditForm: FormGroup = new FormGroup({
    employeeName: new FormControl(null, [Validators.required, Validators.maxLength(20)]),
    employeeCharge: new FormControl(null, [Validators.required]),
    employeeEmail: new FormControl(null, [Validators.required, Validators.email]),
    employeeKnowledge: new FormControl(null, [Validators.required]),
    employeeId: new FormControl(),
    employeeCurrentPassword: new FormControl(null, [Validators.required]),
    employeeNewPassword: new FormControl(null, [Validators.required])

  })

  constructor(
        private employeeService: EmployeesService,
        private encry: encrypt
  ){}

   ngOnInit(): void {
    const enId: any=localStorage.getItem('id');
    this.id= this.encry.decryptData(enId)
    this.getEmployeeById(this.id)
  }

  getEmployeeById(id: string | null) {
    this.employeeService.getEmployeeById(id).subscribe(resp => {
      this.myProfile = resp;
      console.log(this.myProfile);
      this.perfilEditForm.patchValue({
        employeeName: resp.employeeName,
        employeeCharge: resp.employeeCharge,
        employeeEmail: resp.employeeEmail,
        employeeKnowledge: resp.employeeKnowledge
      })
    })
  }

  editPerfilEmployee() {
    if (this.perfilEditForm.valid) {
      const data = {
        employeeName: this.perfilEditForm.get('employeeName')?.value,
        employeeCharge: this.perfilEditForm.get('employeeCharge')?.value,
        employeeEmail: this.perfilEditForm.get('employeeEmail')?.value,
        employeePassword:this.perfilEditForm.get('employeeNewPassword')?.value,
        employeeKnowledge: this.perfilEditForm.get('employeeKnowledge')?.value,
      }

      const employeeCurrentPassword= this.perfilEditForm.get('employeeCurrentPassword')?.value;

      this.employeeService.updateEmployee2(this.id, employeeCurrentPassword,data).subscribe((resp) =>{
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Datos de acceso editados',
            showConfirmButton: false,
            timer: 1500,
            toast: true,
            customClass: {
              container: 'my-swal-container',
              title: 'my-swal-title',
              icon: 'my-swal-icon',
              popup: 'my-swal-popup',
            },
          })
        },err =>{
          Swal.fire({
            position: 'top-end',
            icon: 'warning',
            title: 'La contraseña diligenciada no es la actual',
            showConfirmButton: false,
            timer: 1500,
            toast: true,
            customClass: {
              container: 'my-swal-container',
              title: 'my-swal-title',
              icon: 'my-swal-icon',
              popup: 'my-swal-popup',
            },
          })
        })
    }
  }
}
