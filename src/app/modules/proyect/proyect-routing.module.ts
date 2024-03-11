import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { ProyectComponent } from './pages/proyect/proyect.component';
import { ProyectEditComponent } from './pages/proyect-edit/proyect-edit.component';
import { ProyectAddComponent } from './pages/proyect-add/proyect-add.component';
import { SubprojetcComponent } from './pages/subprojetc/subprojetc.component';
import { SubprojetcAddComponent } from './pages/subprojetc-add/subprojetc-add.component';
import { SubprojetcEditComponent } from './pages/subprojetc-edit/subprojetc-edit.component';
import { ProyectSubprojectComponent } from './pages/proyect-subproject/proyect-subproject.component';
import {SubprojectUserStoryComponent} from "./pages/subproject-user-story/subproject-user-story.component";


const routes: Routes = [
  {
    path: '',
    component: ProyectComponent
  },
  {
    path: 'proyect-edit/:proyectId',
    component: ProyectEditComponent
  },
  {
    path: 'proyect-add',
    component: ProyectAddComponent
  },
  {
    path: 'proyect-subproject',
    component: ProyectSubprojectComponent
  },
  {
    path: 'subproject',
    component: SubprojetcComponent
  },
  {
    path: 'subproject-add',
    component: SubprojetcAddComponent
  },
  {
    path: 'subproject-edit/:subProjectId',
    component: SubprojetcEditComponent
  },
  {
    path: 'subproject-user-story',
    component: SubprojectUserStoryComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProyectRoutingModule { }
