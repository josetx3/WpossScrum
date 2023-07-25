import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Client } from 'src/app/modules/customer/pages/interface/customer';
import { CustomerService } from 'src/app/modules/customer/pages/service/customer.service';
import { Subproject } from 'src/app/modules/proyect/pages/interface/subprojects';
import { SubprojectService } from 'src/app/modules/proyect/pages/service/subproject.service';
import { Proposal } from '../interface/proposal';
import { ProposalService } from '../../service/proposal.service';
import Swal from 'sweetalert2';

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

  pdfFiles: File | null=null;
  proposal: Proposal[]=[];
  subProjects: Subproject[]=[];
  customers: Client[]=[];
  public base64String: any;

  ngOnInit(){
    this.getAllSubprojects();
    this.getAllCustomer();
  }

  constructor(
    private subprojectService: SubprojectService,
    private customerService: CustomerService,
    private proposalService: ProposalService
  ){}

  getAllSubprojects(){
    this.subprojectService.getAllSubprojects().subscribe({
      next: (resp)=>{
        this.subProjects= resp;
      }
    })
  }

  getAllCustomer(){
    this.customerService.getAllCustomer().subscribe({
      next: (resp)=>{
        this.customers=resp;
      }
    })
  }

  onFileSelected(event: any) {

    let archive = event.target.files[0];
    let reader = new FileReader();

    reader.readAsDataURL(archive);
    reader.onload = () => {
    // Obtener la URL de datos codificada en base64 de la imagen cargada
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
}


