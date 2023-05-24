import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './layout/landing-page/landing-page.component';
import { MainComponent } from './layout/main/main.component';

const routes: Routes = [
  {
    path: '',
    component: LandingPageComponent
  },
  {
    path: 'auth',
    loadChildren: () => import('./modules/auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: 'app',
    component: MainComponent,
    children: [
      {
        path: '',
        loadChildren: () => import('./modules/dashboard/dashboard.module').then(m => m.DashboardModule)
      },
      {
        path: 'area',
        loadChildren: () => import ('./modules/area/area.module').then(m => m.AreaModule)
      },
      {
        path: 'customer',
        loadChildren: () => import ('./modules/customer/customer.module').then(m => m.CustomerModule)
      },
      {
        path: 'proyect',
        loadChildren: () => import('./modules/proyect/proyect.module').then(m => m.ProyectModule)
      },
      {
        path: 'user-story',
        loadChildren: () => import('./modules/userStory/user-story.module').then(m => m.UserStoryModule)
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
