import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TasksRoutingModule } from './tasks-routing.module';
import { FormsModule } from '@angular/forms';
import { EstimateTasksHuComponent } from './pages/estimate-tasks-hu/estimate-tasks-hu.component';



@NgModule({
  declarations: [
    EstimateTasksHuComponent
  ],
  imports: [
    CommonModule,
    TasksRoutingModule,
    FormsModule
  ]
})
export class TasksModule { }
