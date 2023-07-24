import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-manage-proposal',
  templateUrl: './manage-proposal.component.html',
  styleUrls: ['./manage-proposal.component.scss']
})
export class ManageProposalComponent {
  addProposalForm: FormGroup= new FormGroup({
    
  })

  subProjects: any='';
  customers:any='';

  addProposal(){}
}
