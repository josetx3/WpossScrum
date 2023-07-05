import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManageSprintsComponent } from './pages/manage-sprints/manage-sprints.component';
import { AddUserStorySprintComponent } from './pages/add-user-story-sprint/add-user-story-sprint.component';
import { CalculateSprintPointsComponent } from './pages/calculate-sprint-points/calculate-sprint-points.component';
import { EditEmployeeSprintComponent } from './pages/edit-employee-sprint/edit-employee-sprint.component';
import { ScoreSprintDaysComponent } from './pages/score-sprint-days/score-sprint-days.component';
import { SprintAddComponent } from './pages/sprint-add/sprint-add.component';
import { SprintUserStoryManageComponent } from './pages/sprint-user-story-manage/sprint-user-story-manage.component';


const routes: Routes = [
  {
    path: '',
    component: ManageSprintsComponent
  },
  {
    path:'add-userStory-sprint/:sprintId/:areaId',
    component: AddUserStorySprintComponent
  },
  {
    path: 'calculateSprintPoints/:sprintId/:teamId',
    component: CalculateSprintPointsComponent
  },
  {
    path: 'edit-add-employee-sprint/:sprintEmployeeId/:sprintDays/:sprintId/:teamId',
    component: EditEmployeeSprintComponent
  },
  {
    path: 'score-sprint-days/:sprintId',
    component: ScoreSprintDaysComponent
  },
  {
    path: 'sprint-add',
    component: SprintAddComponent
  },
  {
    path: 'sprint-user-story/:sprintId',
    component: SprintUserStoryManageComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SprintsRoutingModule { }
