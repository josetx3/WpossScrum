import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { EstimateTasksHuComponent } from './pages/estimate-tasks-hu/estimate-tasks-hu.component';
import { AddTasksHuComponent } from './pages/add-tasks-hu/add-tasks-hu.component';

const routes: Routes=[
  {
    path:'',
    component: EstimateTasksHuComponent
  },
  {
    path:'app-add-tasks-hu',
    component: AddTasksHuComponent
  },
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TasksRoutingModule { }
