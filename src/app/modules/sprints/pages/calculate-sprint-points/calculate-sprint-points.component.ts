import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TeamsService } from 'src/app/modules/teams/pages/service/teams.service';
import { SprintsService } from '../service/sprints.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { EmployeesService } from 'src/app/modules/employees/pages/service/employees.service';
import { EditEmployeeSprintComponent } from '../edit-employee-sprint/edit-employee-sprint.component';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

@Component({
  selector: 'app-calculate-sprint-points',
  templateUrl: './calculate-sprint-points.component.html',
  styleUrls: ['./calculate-sprint-points.component.scss'],
})
export class CalculateSprintPointsComponent implements OnInit {
  calculateSprintForm: FormGroup = new FormGroup({
    employeeId: new FormControl(null, [Validators.required]),
    sprintEmployeePercentage: new FormControl(null, [Validators.required,Validators.max(100)]),
    sprintEmployeeDescription: new FormControl(null, [Validators.required]),
    sprintEmployeeDay: new FormControl(null, [Validators.required]),
  });

  teamId: string | null = '';
  employees: any;
  sprintId: string | null = '';
  sprintDays: any;
  employeeName: string | null = '';
  sprintEmployeePercentage: any;
  finalCalculation: any;
  teamName: string | null = '';
  sprintEmployeeDay: any;
  employeeListFinal: any;
  employeePercentageFinal: any;
  employeeId: string = '';

  listSprint: [] = [];

  constructor(
    public teamService: TeamsService,
    public sprintService: SprintsService,
    public route: ActivatedRoute,
    public employeesService: EmployeesService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.teamId = this.route.snapshot.paramMap.get('teamId');
    this.sprintId = this.route.snapshot.paramMap.get('sprintId');

    this.getAllCalculationPercentageEmployee();
    this.getBySprintId();
    this.getTeamNameById();
    this.selectEmploye();
  }

  selectEmploye() {
    this.employeesService.getEmployeeToTeam(this.teamId).subscribe({
      next: (r) => {
        this.employees = r;
      },
    });
  }

  getBySprintId() {
    this.sprintService.getSprintById(this.sprintId).subscribe({
      next: (resp) => {
        this.sprintDays = resp.sprintDay;
      },
    });
  }

  getTeamNameById() {
    this.teamService.getTeamById(this.teamId).subscribe({
      next: (resp) => {
        this.teamName = resp.teamName;
      },
    });
  }

  getAllCalculationPercentageEmployee() {
    console.log("SPRINTID  " + this.sprintId);
    console.log("TEAMID  " + this.teamId);
    this.sprintService.getSprintEmployeeByTeamAndSprint(this.sprintId, this.teamId).subscribe({next: (resp) => {
      this.employeeListFinal = resp;
        },
      });
  }

  saveSprintPoints() {
    if (this.calculateSprintForm.valid) {
      this.sprintEmployeeDay = this.calculateSprintForm.get('sprintEmployeeDay')?.value;
      this.sprintEmployeePercentage = this.calculateSprintForm.get('sprintEmployeePercentage')?.value;
      this.employeeName = this.calculateSprintForm.get('employeeName')?.value;
      console.log("EMPLOYEENAME   " +  this.employeeName);

      const data = {
        idEmployee: this.calculateSprintForm.get('employeeId')?.value,
        idSprint: this.sprintId,
        employeeName: this.calculateSprintForm.get('employeeName')?.value,
        daysLeave: this.sprintEmployeeDay,
        observations: this.calculateSprintForm.get('sprintEmployeeDescription')
          ?.value,
        percentage: this.sprintEmployeePercentage,
        percentageFinal:
          (this.sprintEmployeePercentage *
            (this.sprintDays - this.sprintEmployeeDay)) /
          100,
      };
      this.sprintService.saveCalculationSprintPoints(data).subscribe({
        next: () => {
          this.calculateSprintForm.reset();
          this.getAllCalculationPercentageEmployee();

          Swal.fire(
            'Empleado Añadido Correctamente!',
            'Presione el Boton (OK) para Continuar',
            'success'
          );
        },error: () =>{
          Swal.fire(
            'Ese empleado ya esta registrado!',
            'Presione el Boton (OK) para Continuar',
            'warning'
          );
        }
      });
    } else {
      Swal.fire({
        icon: 'warning',
        title: 'Formulario Invalido',
        text: 'Porfavor verifique que todos los campos se llenen correctamente',
      });
      this.calculateSprintForm.markAllAsTouched();
    }
  }

  calculateSprintPoints() {
    this.finalCalculation = 0;
    this.employeeListFinal.forEach(
      (employeesFinalPoints: { percentageFinal: any }) => {
        this.finalCalculation =
          this.finalCalculation + employeesFinalPoints.percentageFinal;
      }
    );
    this.getAllCalculationPercentageEmployee();
    const data = {
      sprintScore: this.finalCalculation,
    };
    this.sprintService.saveScoreSprintFinal(this.sprintId, data).subscribe({
      next: () => {
        Swal.fire(
          'Puntos Establecidos!',
          'Presione el Boton (OK) para Continuar',
          'success'
        );
      },
    });
  }

  editEmployeeTeamSprint(employeeId: number) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '500px';
    dialogConfig.data = { projectId: employeeId };

    const dialogRef = this.dialog.open(
      EditEmployeeSprintComponent,
      dialogConfig
    );

    dialogRef.afterClosed().subscribe((result) => {
      this.getAllCalculationPercentageEmployee();
    });
  }
}
