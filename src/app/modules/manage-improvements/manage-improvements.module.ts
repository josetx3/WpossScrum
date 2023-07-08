import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManageImprovementsComponent } from './pages/manage-improvements/manage-improvements.component';
import { ManageImprovementsAddComponent } from './pages/manage-improvements-add/manage-improvements-add.component';
import { ManageImprovementsSeeComponent } from './pages/manage-improvements-see/manage-improvements-see.component';
import { ManageImprovementsRoutingModule } from './manage-improvements-routing.module';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    ManageImprovementsComponent,
    ManageImprovementsAddComponent,
    ManageImprovementsSeeComponent
  ],
  imports: [
    CommonModule,
    ManageImprovementsRoutingModule,
    ReactiveFormsModule
  ]
})
export class ManageImprovementsModule { }
