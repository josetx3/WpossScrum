import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmployeeComponent } from './pages/employee/employee.component';
import { EmployeeAddComponent } from './pages/employee-add/employee-add.component';
import { EmployeeEditComponent } from './pages/employee-edit/employee-edit.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { EmployeesRoutingModule } from './employees-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EmployeeProfileComponent } from './pages/employee-profile/employee-profile.component';
import { EmployeTeamsComponent } from './pages/employe-teams/employe-teams.component';


@NgModule({
  declarations: [
    EmployeeComponent,
    EmployeeAddComponent,
    EmployeeEditComponent,
    EmployeeProfileComponent,
    EmployeTeamsComponent

  ],
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    EmployeesRoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class EmployeesModule {}
