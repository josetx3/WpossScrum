import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AreaComponent } from './pages/area/area.component';

const routes: Routes = [
  {
    path: '',
    component: AreaComponent
  },
  {
    path: 'edit_area/:areaId',
    component: AreaComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AreaRoutingModule { }
