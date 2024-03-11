import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './pages/customer/customer.component';
import { CustomerEditComponent } from './pages/customer-edit/customer-edit.component';


const routes: Routes = [
  {
    path: '',
    component: CustomerComponent
  },
  {
    path:'customer_edit_form/:clientId',
    component:CustomerEditComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
