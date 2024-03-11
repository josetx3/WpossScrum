import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ManageImprovementsComponent } from './pages/manage-improvements/manage-improvements.component';
import { ManageImprovementsAddComponent } from './pages/manage-improvements-add/manage-improvements-add.component';
import { ManageImprovementsSeeComponent } from './pages/manage-improvements-see/manage-improvements-see.component';
import { AddObservationTypeComponent } from './pages/add-observation-type/add-observation-type.component';



const routes: Routes = [
  {
    path: '',
    component: ManageImprovementsComponent
  },
  {
    path: 'add',
    component: ManageImprovementsAddComponent
  },
  {
    path: 'see/:improvementsId',
    component: ManageImprovementsSeeComponent
  },
  {
    path: 'app-add-observation-type',
    component: AddObservationTypeComponent
  }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class ManageImprovementsRoutingModule { }
