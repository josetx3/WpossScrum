import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TeamAddEmployeeComponent } from './pages/team-add-employee/team-add-employee.component';
import { TeamAddManageComponent } from './pages/team-add-manage/team-add-manage.component';
import { TeamManageComponent } from './pages/team-manage/team-manage.component';
import { TeamManageEditComponent } from './pages/team-manage-edit/team-manage-edit.component';
import { TeamTasksComponent } from './pages/team-tasks/team-tasks.component';
import { TeamTasksEditComponent } from './pages/team-tasks-edit/team-tasks-edit.component';
import { TeamsRoutingModule } from './teams-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {MatSortModule} from "@angular/material/sort";
import { TeamEmployeesListComponent } from './team-employees-list/team-employees-list.component';
import { MatFormFieldModule } from '@angular/material/form-field';


@NgModule({
  declarations: [
    TeamAddEmployeeComponent,
    TeamAddManageComponent,
    TeamManageComponent,
    TeamManageEditComponent,
    TeamTasksComponent,
    TeamTasksEditComponent,
    TeamEmployeesListComponent
  ],
  imports: [
    CommonModule,
    TeamsRoutingModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    FormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    MatSortModule
  ]
})
export class TeamsModule { }
