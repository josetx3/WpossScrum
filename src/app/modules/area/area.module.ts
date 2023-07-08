import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AreaComponent } from './pages/area/area.component';
import { AreaEditComponent } from './pages/area-edit/area-edit.component';
import { AreaRoutingModule } from './area-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  declarations: [AreaComponent, AreaEditComponent],
  imports: [
    CommonModule,
    AreaRoutingModule,
    ReactiveFormsModule,
    MatButtonModule,
  ],
})
export class AreaModule {}
