import { Component, Inject, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import { SprintsService } from '../service/sprints.service';
import Swal from 'sweetalert2';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-edit-employee-sprint',
  templateUrl: './edit-employee-sprint.component.html',
  styleUrls: ['./edit-employee-sprint.component.scss']
})
export class EditEmployeeSprintComponent implements OnInit{
  employeeSprintEditForm: FormGroup = new FormGroup({});
  sprintEmployeeId: string | null = '';
  sprintDays: string | null = '';
  employeePercentageFinal: number = 0;
  teamId: string | null = '';
  sprintId: string | null = '';
  employee:any;
  employeeName:string | null = '';

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    public sprintService:SprintsService,
    private routeSprintCalculation : Router,
    public dialogRef: MatDialogRef<EditEmployeeSprintComponent>,
    @Inject(MAT_DIALOG_DATA) public data1: any
  ) {
  }

  ngOnInit(): void {
    this.teamId =  this.data1.teamId;
    this.sprintId = this.data1.SprintId;
    this.sprintEmployeeId =  this.data1.projectId;
    this.sprintDays = this.data1.sprintDays;

    //console.log("team: "+ this.teamId+"  sprint "+ this.sprintId+ " employee "+this.sprintEmployeeId+ " days "+ this.sprintDays);

    this.employeeSprintEditForm = this.formBuilder.group({
      sprintEmployeePercentage: new FormControl(null, [Validators.required,Validators.min(1),Validators.max(100),Validators.maxLength(3)]),
      sprintEmployeeDay: new FormControl(null, [Validators.required,Validators.max(31),Validators.min(0),Validators.maxLength(2)]),
      sprintEmployeeDescription: new FormControl(null, [Validators.required,Validators.maxLength(150)])
    })
    this.getAllEmployeeSprint();
  }
  getAllEmployeeSprint(){
    //console.log("ASD");
    this.sprintService.getAllEmployeeSprint(this.sprintEmployeeId, this.sprintId).subscribe({
      next:(data)=>{
        this.employee=data;
        this.employeeName=data.employeeName;
        this.employeeSprintEditForm.patchValue({
          sprintEmployeePercentage:this.employee.percentage,
          sprintEmployeeDay:this.employee.daysLeave,
          sprintEmployeeDescription:this.employee.observations
        })
      }
    })
  }
  employeeEdit(){
    if (this.employeeSprintEditForm.valid){
      let sprintEmployeePercentage = this.employeeSprintEditForm.get('sprintEmployeePercentage')?.value;
      let sprintEmployeeDay =  this.employeeSprintEditForm.get('sprintEmployeeDay')?.value;
      const sprintDays = Number(this.sprintDays)

      const data = {
        idEmployee:this.sprintEmployeeId,
        idSprint:this.sprintId,
        percentage: sprintEmployeePercentage,
        percentageFinal: ((sprintEmployeePercentage) * ((sprintDays)-(sprintEmployeeDay)))/100,
        daysLeave:sprintEmployeeDay,
        observations: this.employeeSprintEditForm.get('sprintEmployeeDescription')?.value
      }
      //console.log("el porcebtaje: "+data.percentageFinal+" dias sprint: "+sprintDays+" dias employee: "+sprintEmployeeDay);
      this.sprintService.updateEmployeeSprint(this.sprintEmployeeId, this.sprintId,data).subscribe({
        next:()=>{
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Datos actualizados',
            showConfirmButton: false,
            timer: 1500,
            toast: true,
            customClass: {
              container: 'my-swal-container',
              title: 'my-swal-title',
              icon: 'my-swal-icon',
            },
            background: '#E6F4EA',
          });
          this.dialogRef.close();
          this.employeeSprintEditForm.reset();
         // this.routeSprintCalculation.navigateByUrl('/app/sprints/calculateSprintPoints/' + this.sprintId).then();
         // this.employeeSprintEditForm.reset();
        }
      })
    }else {
      Swal.fire({
        icon: 'warning',
        title: 'Formulario Invalido',
        text: 'Porfavor verifique que todos los campos se llenen correctamente'
      })
      this.employeeSprintEditForm.markAllAsTouched();
    }
  }
}
