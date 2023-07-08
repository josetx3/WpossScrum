import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TasksRoutingModule } from './tasks-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EstimateTasksHuComponent } from './pages/estimate-tasks-hu/estimate-tasks-hu.component';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    EstimateTasksHuComponent
  ],
  imports: [
    CommonModule,
    TasksRoutingModule,
    ReactiveFormsModule,
    MatButtonModule,
    FormsModule
  ]
})
export class TasksModule { }
