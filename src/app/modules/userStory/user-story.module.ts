import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserStoryComponent } from './pages/user-story/user-story.component';
import { UserStoryManagementComponent } from './pages/user-story-management/user-story-management.component';
import { UserStoryAddComponent } from './pages/user-story-add/user-story-add.component';
import { UserStoryRoutingModule } from './user-story-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { StateUserStoryAddComponent } from './pages/state-user-story-add/state-user-story-add.component';



@NgModule({
  declarations: [
    UserStoryComponent,
    UserStoryManagementComponent,
    UserStoryAddComponent,
    StateUserStoryAddComponent
  ],
  imports: [
    CommonModule,
    UserStoryRoutingModule,
    ReactiveFormsModule,
    MatButtonModule
  ]
})
export class UserStoryModule { }
