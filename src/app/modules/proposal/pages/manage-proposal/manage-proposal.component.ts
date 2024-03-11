import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Client } from 'src/app/modules/customer/pages/interface/customer';
import { CustomerService } from 'src/app/modules/customer/pages/service/customer.service';
import { Subproject } from 'src/app/modules/proyect/pages/interface/subprojects';
import { SubprojectService } from 'src/app/modules/proyect/pages/service/subproject.service';
import { Proposal } from '../interface/proposal';
import { ProposalService } from '../../service/proposal.service';
import Swal from 'sweetalert2';
import { MatDialog } from '@angular/material/dialog';
import { EditProposalComponent } from '../edit-proposal/edit-proposal.component';

@Component({
  selector: 'app-manage-proposal',
  templateUrl: './manage-proposal.component.html',
  styleUrls: ['./manage-proposal.component.scss']
})
export class ManageProposalComponent {

  addProposalForm: FormGroup= new FormGroup({
    clienteId: new FormControl(null, Validators.required),
    subProjectId: new FormControl(null, Validators.required),
    proposalName: new FormControl(null, Validators.required),
    proposalObservation: new FormControl(null, Validators.required),
    proposalArchive: new FormControl(null, Validators.required)
  })

  proposal: Proposal[]=[];
  subProjects: Subproject[]=[];
  customers: Client[]=[];
  public base64String: any;


  ngOnInit(){
    this.getAllCustomer();
    this.getAllProposal()
  }

  constructor(
    private subprojectService: SubprojectService,
    private customerService: CustomerService,
    private proposalService: ProposalService,
    private matDialog: MatDialog
  ){}

  getAllCustomer(){
    this.customerService.getAllCustomer().subscribe({
      next: (resp)=>{
        this.customers=resp;
      }
    })
  }

  getAllSubprojects(){
   const clientId= this.addProposalForm.get('clienteId')?.value
    this.subprojectService.getSubprojectByCustomer(clientId).subscribe({
      next: (resp)=>{
        this.subProjects= resp;
      }, 
      error: (err)=>{
        this.subProjects=[];
      }
    })
  }

  onFileSelected(event: any) {

    let archive = event.target.files[0];
    let reader = new FileReader();

    reader.readAsDataURL(archive);
    reader.onload = () => {
    let pdfDataURL = reader.result?.toString().split(',')[1];
    this.base64String = pdfDataURL;
    
    };
  }

  addProposal(){
    const dataProposal={
      clientId: this.addProposalForm.get('clienteId')?.value,
      subprojectId: this.addProposalForm.get('subProjectId')?.value,
      proposalName: this.addProposalForm.get('proposalName')?.value,
      proposalDescription: this.addProposalForm.get('proposalObservation')?.value,
      proposalState: 'ENVIADO',
      proposalArchive: this.base64String
    }
    console.log(dataProposal)
    this.proposalService.saveProposal(dataProposal).subscribe({
      next: (resp)=>{
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Propuesta guardad exitosamente',
          showConfirmButton: false,
          timer: 1500,
          toast: true,
          customClass: {
            container: 'my-swal-container',
            title: 'my-swal-title',
            icon: 'my-swal-icon',
          },
          background: '#E6F4EA',
        })
      },
      error: (err)=>{
        Swal.fire({
          position: 'top-end',
          icon: 'warning',
          title: 'Hubo un error al guardar la propuesta',
          showConfirmButton: false,
          timer: 1500,
          toast: true,
          customClass: {
            container: 'my-swal-container',
            title: 'my-swal-title',
            icon: 'my-swal-icon',
          },
          background: '#FFFEFB',
        })
      }
    })
  }

  getAllProposal(){
    this.proposalService.getAllProposal().subscribe({
      next: (resp)=>{
        this.proposal=resp;
        
      }
    })
  }

  viewPdf(proposalArchive: string, proposalName: string){
    const base64String = proposalArchive; 
    const filename = proposalName+".pdf"; 

    this.downloadPDFFromBase64(base64String, filename);
  }

   downloadPDFFromBase64(base64: string, filename: string) {
    const blob = this.base64toBlob(base64, 'application/pdf');
    const url = URL.createObjectURL(blob);
  
    const a = document.createElement('a');
    a.href = url;
    a.download = filename;
    a.style.display = 'none';
  
    document.body.appendChild(a);
    a.click();
    window.URL.revokeObjectURL(url);
  }

  base64toBlob(base64: string, type: string): Blob {
    const binaryString = atob(base64);
    const len = binaryString.length;
    const bytes = new Uint8Array(len);
  
    for (let i = 0; i < len; ++i) {
      bytes[i] = binaryString.charCodeAt(i);
    }
  
    return new Blob([bytes], { type: type });
  }

  updateProposal(proposalId: string){
      const dialog= this.matDialog.open(EditProposalComponent,{
        width: '500px',
        data:{proposalId:proposalId}
      })

      dialog.afterClosed().subscribe(
        ()=>this.getAllProposal()
      )
  }
}


