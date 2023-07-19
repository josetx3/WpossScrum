import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddUserStorySprintComponent } from './pages/add-user-story-sprint/add-user-story-sprint.component';
import { CalculateSprintPointsComponent } from './pages/calculate-sprint-points/calculate-sprint-points.component';
import { EditEmployeeSprintComponent } from './pages/edit-employee-sprint/edit-employee-sprint.component';
import { ManageSprintsComponent } from './pages/manage-sprints/manage-sprints.component';
import { ScoreSprintDaysComponent } from './pages/score-sprint-days/score-sprint-days.component';
import { SprintAddComponent } from './pages/sprint-add/sprint-add.component';
import { SprintsRoutingModule } from './sprints-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { SprintUserStoryManageComponent } from './pages/sprint-user-story-manage/sprint-user-story-manage.component';
import { EditSprintUserStoryManageComponent } from './pages/edit-sprint-user-story-manage/edit-sprint-user-story-manage.component';
import { GraphicBurndownchartComponent } from './pages/graphic-burndownchart/graphic-burndownchart.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';


@NgModule({
  declarations: [
    AddUserStorySprintComponent,
    CalculateSprintPointsComponent,
    EditEmployeeSprintComponent,
    ManageSprintsComponent,
    ScoreSprintDaysComponent,
    SprintAddComponent,
    SprintUserStoryManageComponent,
    EditSprintUserStoryManageComponent,
    GraphicBurndownchartComponent,
  ],
  imports: [
    CommonModule,
    SprintsRoutingModule,
    ReactiveFormsModule,
    MatButtonModule,
    NgxChartsModule,
    FormsModule
  ]
})
export class SprintsModule { }
