import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ManageProposalComponent } from '../manage-proposal/manage-proposal.component';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Proposal } from '../interface/proposal';
import { ProposalService } from '../../service/proposal.service';

@Component({
  selector: 'app-edit-proposal',
  templateUrl: './edit-proposal.component.html',
  styleUrls: ['./edit-proposal.component.scss']
})
export class EditProposalComponent {
  proposalId: string='';
  proposal: Proposal[]=[];

  editProposalForm: FormGroup= new FormGroup({
    proposalName: new FormControl(null, [Validators.required]),
    proposalObservation: new FormControl(null, [Validators.required]),
    proposalStatus: new FormControl(null, [Validators.required])
  })

  ngOnInit(){
    this.proposalId=this.dataProposal.proposalId;
    this.getProposalByiD();

  }

  constructor(
    private proposalService: ProposalService,
    @Inject(MAT_DIALOG_DATA) public dataProposal: any,
    private dialogRef: MatDialogRef<ManageProposalComponent>
  ){}

  getProposalByiD(){
    this.proposalService.getProposalById(this.proposalId).subscribe({
      next:(resp)=>{
        this.proposal=resp;
        console.log(resp)
        this.editProposalForm.patchValue({
          proposalName:resp.proposalName ,
          proposalObservation: resp.proposalDescription,
          proposalStatus: resp.proposalState
        })
      }
    })
  }

  updateProposal(){}
}
