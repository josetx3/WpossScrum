import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { UserStoryComponent } from './pages/user-story/user-story.component';
import { UserStoryManagementComponent } from './pages/user-story-management/user-story-management.component';
import { UserStoryAddComponent } from './pages/user-story-add/user-story-add.component';
import { StateUserStoryAddComponent } from './pages/state-user-story-add/state-user-story-add.component';



const routes: Routes = [
  {
    path: '',
    component: UserStoryComponent
  },
  {
    path:'user_story_management_form/:userStoryId',
    component: UserStoryManagementComponent
  },
  {
    path: 'subprojet-add',
    component: UserStoryAddComponent
  },
  {
    path: 'app-state-user-story-add',
    component: StateUserStoryAddComponent
  }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class UserStoryRoutingModule { }
