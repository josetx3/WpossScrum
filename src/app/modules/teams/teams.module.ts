import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TeamAddEmployeeComponent } from './pages/team-add-employee/team-add-employee.component';
import { TeamAddManageComponent } from './pages/team-add-manage/team-add-manage.component';
import { TeamManageComponent } from './pages/team-manage/team-manage.component';
import { TeamManageEditComponent } from './pages/team-manage-edit/team-manage-edit.component';
import { TeamTasksComponent } from './pages/team-tasks/team-tasks.component';
import { TeamTasksEditComponent } from './pages/team-tasks-edit/team-tasks-edit.component';
import { TeamEmployeesListComponent } from './pages/team-employees-list/team-employees-list.component';



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
    CommonModule
  ]
})
export class TeamsModule { }
