import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: FormGroup = new FormGroup({
    EmployeeEmail: new FormControl(null, [Validators.required]),
    EmployeePassword: new FormControl(null, [Validators.required]),
  });

  EmployeeEmail: String= '';
  EmployeePassword: String='';

  constructor(){}



}
