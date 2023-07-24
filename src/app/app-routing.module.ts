import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './layout/landing-page/landing-page.component';
import { MainComponent } from './layout/main/main.component';
import { AuthGuard } from './guard/auth.guard';

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
    canActivate:[AuthGuard],
    component: MainComponent,
    children: [
      {
        path: '',
        loadChildren: () => import('./modules/dashboard/dashboard.module').then(m => m.DashboardModule),
      },
      {
        path: 'user-story',
        loadChildren: () => import('./modules/userStory/user-story.module').then(m => m.UserStoryModule)
      },
      {
        path: 'customer',
        loadChildren: () => import('./modules/customer/customer.module').then(m => m.CustomerModule)
      },
      {
        path: 'area',
        loadChildren: () => import('./modules/area/area.module').then(m => m.AreaModule)
      },
      {
        path: 'employees',
        loadChildren: () => import('./modules/employees/employees.module').then(m => m.EmployeesModule)
      },
      {
        path: 'teams',
        loadChildren: () => import('./modules/teams/teams.module').then(m => m.TeamsModule)
      },
      {
        path: 'sprints',
        loadChildren: () => import('./modules/sprints/sprints.module').then(m => m.SprintsModule)
      },
      {
        path: 'board',
        loadChildren: () => import('./modules/manage-board/manage-board.module').then(m => m.ManageBoardModule)
      },

      {
        path: 'improvements',
        loadChildren: () => import('./modules/manage-improvements/manage-improvements.module').then(m => m.ManageImprovementsModule)
      },

      {
        path: 'proyect',
        loadChildren: () => import('./modules/proyect/proyect.module').then(m => m.ProyectModule)
      },
      {
        path: 'tasks',
        loadChildren: () => import('./modules/tasks/tasks.module').then(m => m.TasksModule)
      },
      {
        path: 'proposal',
        loadChildren: () => import('./modules/proposal/proposal.module').then(m => m.ProposalModule)
      },
      {
        path:'**',
        redirectTo:'/app',
        pathMatch:'full'
      }
    ]
  },
  {
    path:'**',
    redirectTo:'',
    pathMatch:'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
