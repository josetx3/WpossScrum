import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManageProposalComponent } from './pages/manage-proposal/manage-proposal.component';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: ManageProposalComponent,
  }
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
  
})
export class ProposalRoutingModule { }
