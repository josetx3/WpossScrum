import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: FormGroup = new FormGroup({
    employeeEmail: new FormControl(null, [Validators.required]),
    employeePassword: new FormControl(null, [Validators.required]),
  });

  employeeEmail: String= '';
  employeePassword: String='';

  constructor(
    private authService: AuthService,
    private router: Router
  ){}

  login() {
    console.log('entra ');
    if (this.loginForm.valid){
      const data = {
        employeeEmail:this.loginForm.get('employeeEmail')?.value,
        employeePassword:this.loginForm.get('employeePassword')?.value,
      }
      console.log(JSON.stringify(data))
        this.authService.PostEmailAndClave(data).subscribe(resp => {
        localStorage.setItem('token', resp.token);
        console.log('resp contiene: '+resp.token);
        this.router.navigate(['/app'])
      })
    }
  }





}
