import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import { SprintsService } from '../service/sprints.service';
import Swal from 'sweetalert2';

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
    private routeSprintCalculation : Router
  ) {
  }

  ngOnInit(): void {
    this.teamId = this.route.snapshot.params['teamId'];//.get('');
    this.sprintId = this.route.snapshot.paramMap.get('sprintId');
    this.sprintEmployeeId = this.route.snapshot.paramMap.get('sprintEmployeeId');
    this.sprintDays = this.route.snapshot.paramMap.get('sprintDays');

    console.log("team: "+ this.teamId+"  sprint "+ this.sprintId+ " employee "+this.sprintEmployeeId+ " days "+ this.sprintDays);

    this.employeeSprintEditForm = this.formBuilder.group({
      sprintEmployeePercentage: new FormControl(null, [Validators.required,Validators.min(1),Validators.max(100),Validators.maxLength(3)]),
      sprintEmployeeDay: new FormControl(null, [Validators.required,Validators.max(31),Validators.min(0),Validators.maxLength(2)]),
      sprintEmployeeDescription: new FormControl(null, [Validators.required,Validators.maxLength(150)])
    })
    this.getAllEmployeeSprint();
  }
  getAllEmployeeSprint(){
    //console.log("ASD");
    this.sprintService.getAllEmployeeSprint(this.sprintEmployeeId).subscribe({
      next:(data)=>{

        this.employee=data;
        this.employeeName=data.employeeSprint.employeeName;
        this.employeeSprintEditForm.patchValue({
          sprintEmployeePercentage:this.employee.sprintEmployeePercentage,
          sprintEmployeeDay:this.employee.sprintEmployeeDay,
          sprintEmployeeDescription:this.employee.sprintEmployeeDescription
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
        sprintId: this.sprintId,
        sprintEmployeePercentage: sprintEmployeePercentage,
        employeePercentageFinal: ((sprintEmployeePercentage) * ((sprintDays)-(sprintEmployeeDay)))/100,
        sprintEmployeeDay:sprintEmployeeDay,
        sprintEmployeeDescription: this.employeeSprintEditForm.get('sprintEmployeeDescription')?.value
      }
      this.sprintService.updateEmployeeSprint(this.sprintEmployeeId,data).subscribe({
        next:()=>{
          this.routeSprintCalculation.navigateByUrl('/app/sprints/calculateSprintPoints/' + this.sprintId + '/' + this.teamId).then();
          this.employeeSprintEditForm.reset();

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
