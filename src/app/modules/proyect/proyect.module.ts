import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProyectComponent } from './pages/proyect/proyect.component';
import { ProyectAddComponent } from './pages/proyect-add/proyect-add.component';
import { ProyectEditComponent } from './pages/proyect-edit/proyect-edit.component';
import { SubprojetcComponent } from './pages/subprojetc/subprojetc.component';
import { SubprojetcAddComponent } from './pages/subprojetc-add/subprojetc-add.component';
import { SubprojetcEditComponent } from './pages/subprojetc-edit/subprojetc-edit.component';
import { ProyectRoutingModule } from './proyect-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  declarations: [
    ProyectComponent,
    ProyectAddComponent,
    ProyectEditComponent,
    SubprojetcComponent,
    SubprojetcAddComponent,
    SubprojetcEditComponent,
  ],
  imports: [CommonModule, ProyectRoutingModule, SharedModule],
})
export class ProyectModule {}
