import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { TeamManageComponent } from './pages/team-manage/team-manage.component';
import { TeamManageEditComponent } from './pages/team-manage-edit/team-manage-edit.component';
import { TeamTasksComponent } from './pages/team-tasks/team-tasks.component';
import { TeamTasksEditComponent } from './pages/team-tasks-edit/team-tasks-edit.component';
import { TeamAddEmployeeComponent } from './pages/team-add-employee/team-add-employee.component';



const routes: Routes = [
  {
    path: '',
    component: TeamManageComponent
  },
  {
    path: 'teams-edit/:teamId',
    component: TeamManageEditComponent
  },
  {
    path: 'add-employee.ts-team/:teamId',
    component: TeamAddEmployeeComponent
  },
  {
    path: 'team-task',
    component: TeamTasksComponent
  },
  {
    path: 'team-task-edit/:taskTeamId',
    component: TeamTasksEditComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeamsRoutingModule {
}
