import { Component, Inject } from '@angular/core';
import { EmployeesService } from '../service/employees.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-employe-teams',
  templateUrl: './employe-teams.component.html',
  styleUrls: ['./employe-teams.component.scss']
})
export class EmployeTeamsComponent {

  employeeId: string='';
  employeeArea: any='';

  constructor(
    public employeService: EmployeesService,
    public dialogRef : MatDialogRef<EmployeTeamsComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any,
  ){}

  ngOnInit(){
    this.employeeId= this.data.employeeId;
    this.getAreaByEmployee();
  }

  getAreaByEmployee(){
    this.employeService.getAreaByEmployee(this.employeeId).subscribe({
      next: (resp)=>{
          this.employeeArea= resp
      },
      error: (err)=>{
          
      }

    })
  }
}
