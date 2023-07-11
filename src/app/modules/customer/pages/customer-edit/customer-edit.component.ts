import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators} from "@angular/forms";
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { CustomerService } from '../service/customer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerComponent } from '../customer/customer.component';

@Component({
  selector: 'app-customer-edit',
  templateUrl: './customer-edit.component.html',
  styleUrls: ['./customer-edit.component.scss']
})
export class CustomerEditComponent implements OnInit {
  customerForm: FormGroup = new FormGroup({
    clientNit: new FormControl(null, [Validators.required]),
      client_name: new FormControl(null, [Validators.required, Validators.maxLength(20) ])
  });
  customer: any;
  clientNit: string = '';
  clienteNitEdit: string='';


  constructor(
    private customerService: CustomerService,
    private route: ActivatedRoute,
    private route1: Router,
    private dialogRef : MatDialogRef<CustomerComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    ) {
  }

  ngOnInit(): void {
    this.clienteNitEdit = this.data.clientNit;
    this.getCustomerById(this.clienteNitEdit)

  }

  getCustomerById(id: string | null) {
    this.customerService.getCustomerById(id).subscribe(resp => {
      this.customer = resp;
      this.customerForm.patchValue({
        clientNit: this.customer.clientNit,
        client_name: this.customer.client_name
      });
    })
  }

  edit() {
    if (this.customerForm.valid){
      const data ={
        clientNit:this.customerForm.get('clientNit')?.value,
        client_name:this.customerForm.get('client_name')?.value,
      }
      this.customerService.updateCustomer(this.clienteNitEdit,data).subscribe(
        {
          next:(resp) =>{
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Cliente editado',
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
          this.customerForm.reset();
          this.dialogRef.close();
        }
          ,
          error: (err)=>{
          }
        }
        );
    }
  }
}
