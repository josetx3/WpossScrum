import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
    registerForm: FormGroup = new FormGroup({
      employeeName:  new FormControl(null, [Validators.required]),
      employeeCharge:  new FormControl(null, [Validators.required]),
      employeeKnowledge: new FormControl(null, [Validators.required]),
      employeeEmail: new FormControl(null, [Validators.required]),
      employeePassword: new FormControl(null, [Validators.required])
  });

  constructor(
    private authService: AuthService,
    private router: Router,

  ){}

  employeeName: String='';
  employeeCharge: String='';
  employeeKnowledge: String=';'
  employeeEmail: String= '';
  employeePassword: String='';

  register(): void {
    if (this.registerForm.valid) {
      const data = {
        employeeName: this.registerForm.get('employeeName')?.value,
        employeeCharge: this.registerForm.get('employeeCharge')?.value,
        employeeKnowledge: this.registerForm.get('employeeKnowledge')?.value,
        employeeEmail: this.registerForm.get('employeeEmail')?.value,
        employeePassword: this.registerForm.get('employeePassword')?.value,
      }
      this.authService.register(data).subscribe({
        next: () => {
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Registrado exitosamente',
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
          this.registerForm.reset();
        }
        ,
        error: (err)=>{
          Swal.fire({
            position: 'top-end',
            icon: 'warning',
            title: 'El correo ya se encuentra registrado',
            showConfirmButton: false,
            timer: 2000,
            toast: true,
            customClass: {
              container: 'my-swal-container',
              title: 'my-swal-title',
              icon: 'my-swal-icon',
            },
            background: '#FFFEFB',
          })
          console.log(err)
        }
      }
        
      )
      this.router.navigate(['/app/auth'])
    }

  }
}
