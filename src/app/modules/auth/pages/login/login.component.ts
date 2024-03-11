import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { encrypt } from 'src/app/utils/encrypt';



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
    private encry: encrypt

  ){}

  login() {
   // console.log('entra ');
    if (this.loginForm.valid){
      //console.log('entra egrerggeg');
      const data = {
        employeeEmail:this.loginForm.get('employeeEmail')?.value,
        employeePassword:this.loginForm.get('employeePassword')?.value,
      }

      this.authService.PostEmailAndClave(data).subscribe({
        next: resp => {
          const currentTime= new Date();
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Login exitoso',
            showConfirmButton: false,
            timer: 2500,
            toast: true,
            customClass: {
              container: 'my-swal-container',
              title: 'my-swal-title',
              icon: 'my-swal-icon',
            },
            background: '#E6F4EA',
          });

    //  const encryptedData = this.encry.encryptData(originalData);
    //  console.log( "encrip "+encryptedData);
    //  localStorage.setItem("data",encryptedData)
    //  const datos:any= localStorage.getItem('data');
    //  const desencryptedData = this.encry.decryptData(datos);
    //  console.log( "noencrip "+desencryptedDat

          const encryptToken= this.encry.encryptData(resp.token)
          const encryptedName= this.encry.encryptData(resp.nameE)
          const encryptedCharge= this.encry.encryptData(resp.charge)
          const encryptedId= this.encry.encryptData(resp.idE)
          localStorage.setItem('token', encryptToken);
          localStorage.setItem('name', encryptedName);
          localStorage.setItem('charge', encryptedCharge);
          localStorage.setItem('horaInicio', currentTime.toString());
          localStorage.setItem('id',encryptedId);
          this.router.navigate(['/app'])
        },
        error: err =>{
          console.log(err)
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
        }

      })
    }
  }

  ngOnInit():void{

    const token =localStorage.getItem('token');
    console.log()
    if(token==='' || token===null || token===undefined){

    }else{
      this.router.navigate(['/app'])
    }


  }
}
