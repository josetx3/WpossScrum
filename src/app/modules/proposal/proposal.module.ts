import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProposalRoutingModule } from './proposal-routing.module';
import { ManageBoardModule } from '../manage-board/manage-board.module';
import { ManageProposalComponent } from './pages/manage-proposal/manage-proposal.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    ManageProposalComponent
  ],
  imports: [
    CommonModule,
    ProposalRoutingModule,
    ReactiveFormsModule,
    MatButtonModule,
    FormsModule
  ]
})
export class ProposalModule { }
