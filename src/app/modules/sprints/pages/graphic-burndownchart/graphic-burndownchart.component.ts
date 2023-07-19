import { Component } from '@angular/core';

@Component({
  selector: 'app-graphic-burndownchart',
  templateUrl: './graphic-burndownchart.component.html',
  styleUrls: ['./graphic-burndownchart.component.scss']
})
export class GraphicBurndownchartComponent {

  

  view: [number, number] = [900, 500];

  // options
  legend: boolean = true;
  titulo: string= 'Burn down chart';
  autoScale: boolean=true;
  showLabels: boolean = true;
  animations: boolean = true;
  xAxis: boolean = true;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  showXAxisLabel: boolean = true;
  xAxisLabel: string = 'Duracion del sprint (Jornada)';
  yAxisLabel: string = 'Tarbajo realizado (Horas de tareas)';
  timeline: boolean = true;

  colorScheme: any = {
    domain: ['#00A6FE', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5']
  };

  multi = [
    {
      "name": "Horas estimadas",
      "series": [
        {
          "name": "1",
          "value": 75
        },
        {
          "name": "2",
          "value": 60
        },
        {
          "name": "3",
          "value": 45
        },
        {
          "name": "4",
          "value": 30
        },
        {
          "name": "5",
          "value": 15
        },
        {
          "name": "6",
          "value": 0
        }
      ]
    },
  
    {
      "name": "Horas reales",
      "series": [
        {
          "name": "1",
          "value": 75
        },
        {
          "name": "2",
          "value": 63
        },
        {
          "name": "3",
          "value": 50
        },
        {
          "name": "4",
          "value": 48
        },
        {
          "name": "5",
          "value": 10
        },
        {
          "name": "6",
          "value": 0
        }
      ]
    },
  ];
  

  constructor() {
    // Object.assign(this, { multi });
  }

  onSelect(data: any): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data: any): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data: any): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }
}
