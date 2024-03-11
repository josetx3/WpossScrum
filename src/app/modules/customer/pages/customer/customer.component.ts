import { Component, OnInit } from '@angular/core';
import { Customer } from '../interface/customer';
import { CustomerService } from '../service/customer.service';
import { MatDialog } from '@angular/material/dialog';
import { CustomerEditComponent } from '../customer-edit/customer-edit.component';
import { CustomerAddComponent } from '../customer-add/customer-add.component';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomerComponent implements OnInit {
  customer: Customer | any;
  clientNit: string = '';

  constructor(

    private customerService: CustomerService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {

   this.getAllCustomer();

  }

  getAllCustomer(): void{
    this.customerService.getAllCustomer().subscribe(resp => {
      this.customer = resp;
    })
  }

    editCustomerModal(clientNit: string){
      const dialogRef = this.dialog.open(CustomerEditComponent, {width: '500px', data:{clientNit: clientNit}})
      dialogRef.afterClosed().subscribe(resp =>{
        this.getAllCustomer();
      })
    }

    addCustomerModal(){
      const dialogRef = this.dialog.open(CustomerAddComponent, {width: '500px'})
      dialogRef.afterClosed().subscribe(resp =>{
        this.getAllCustomer();
      })
    }

}
