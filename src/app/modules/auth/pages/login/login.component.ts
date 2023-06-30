import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{
  loginForm: FormGroup = new FormGroup({
    employeeEmail: new FormControl(null, [Validators.required]),
    employeePassword: new FormControl(null, [Validators.required]),
  });

  employeeEmail: String= '';
  employeePassword: String='';

  constructor(
    private authService: AuthService,
    private router: Router,

  ){}

  login() {
   // console.log('entra ');
    if (this.loginForm.valid){
      //console.log('entra egrerggeg');
      const data = {
        employeeEmail:this.loginForm.get('employeeEmail')?.value,
        employeePassword:this.loginForm.get('employeePassword')?.value,
      }
      //console.log(JSON.stringify(data))
        this.authService.PostEmailAndClave(data).subscribe(resp => {
        const currentTime= new Date();
         localStorage.setItem('token', resp.token);
        localStorage.setItem('name', resp.nameE);
        localStorage.setItem('charge', resp.charge);
        localStorage.setItem('horaInicio', currentTime.toString() )
        localStorage.setItem('id', resp.idE)
        // console.log('resp contiene: '+resp.token);
        this.router.navigate(['/app'])
      },err =>{
        Swal.fire({
          position: 'top-end',
          icon: 'warning',
          title: 'Las credenciales de acceso son incorrectas',
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

  ngOnInit():void{
    const token = localStorage.getItem('token');
    if(token==='' || token===null || token===undefined){

    }else{
      this.router.navigate(['/app'])
    }


  }
}
