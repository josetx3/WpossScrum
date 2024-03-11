import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DateItem } from '../interface/sprints-interfaces';
import { ActivatedRoute } from '@angular/router';
import { SprintsService } from '../service/sprints.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-score-sprint-days',
  templateUrl: './score-sprint-days.component.html',
  styleUrls: ['./score-sprint-days.component.scss'],
})
export class ScoreSprintDaysComponent implements OnInit {
  dayForm: FormGroup = new FormGroup({
    date: new FormControl(null, [Validators.required]),
    dotteddays: new FormControl(null, [Validators.required]),
  });

  sprintId: string | null = '';
  teamId: string | null='';
  areaId: string | null='';
  sprintStart: any='';
  sprintEnd: any='';
  sprintDay: string | null='';
  arrayDates: any[] = [];
  startDate: any;
  endDate: any;
  currentDate: any;
  DateItem: DateItem[] = [];
  selectedValues: string[] = [];
  totalScore: number = 0;

  constructor(
    private route: ActivatedRoute,
    private sprintService: SprintsService
  ) {}

  ngOnInit(): void {
    this.sprintId = this.route.snapshot.paramMap.get('sprintId');
    this.getAllDatesSprint();
  }

  getAllDatesSprint() {
    this.sprintService.getSprintById(this.sprintId).subscribe({
      next: (data) => {
        console.log(data)
        this.teamId= data.teamId;
        this.areaId= data.areaId;
        this.sprintStart= data.sprintStart;
        this.sprintEnd= data.sprintEnd;
        this.sprintDay= data.sprintDay;

        this.startDate = new Date(data.sprintStart);
        this.endDate = new Date(data.sprintEnd);
        this.endDate.setDate(this.endDate.getDate() + 1)
        console.log(this.startDate)

        this.currentDate = this.startDate;
        this.arrayDates = [];

        while (this.currentDate <= this.endDate) {
          if (this.currentDate.getDay() !== 0) {
            const formattedDate = `${this.currentDate
              .getDate()
              .toString()
              .padStart(2, '0')}/${(this.currentDate.getMonth() + 1)
              .toString()
              .padStart(2, '0')}/${this.currentDate.getFullYear().toString()}`;
            const dateItem: DateItem = {
              date: formattedDate,
            };
            this.arrayDates.push(dateItem);
          }
          this.currentDate.setDate(this.currentDate.getDate() + 1);
        }
      },
    });
  }

  saveDatesSprints() {
    if (this.dayForm.valid) {
      const data = {
        dayp: this.dayForm.get('dotteddays')?.value,
        date: this.dayForm.get('date')?.value,
      };
    }
  }

  CalculateSprintPoints() {
    this.totalScore = this.selectedValues.reduce(
      (sum, value) => sum + parseFloat(value),
      0
    );
  }

  CalculateSprintPoints2(){
    const data={
      teamId: this.teamId,
      areaId: this.areaId,
      sprintStart: this.sprintStart,
      sprintEnd: this.sprintEnd,
      sprintDay: this.sprintDay,
      sprintDaysDate: this.totalScore
    }

    console.log(data)
    this.sprintService.updateSprint(this.sprintId, data).subscribe({

    next: (resp)=>{
      Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Los días punteados hansido guardados con exito',
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
    }
    , 
    error: (err)=>{
      console.log("puntos no guardados")
    }
    })
  }



}
