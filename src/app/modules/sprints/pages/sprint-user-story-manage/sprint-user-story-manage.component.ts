import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-sprint-user-story-manage',
  templateUrl: './sprint-user-story-manage.component.html',
  styleUrls: ['./sprint-user-story-manage.component.scss']
})
export class SprintUserStoryManageComponent {
  sprintId: String | null='';
  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.sprintId = this.route.snapshot.paramMap.get('sprintId');
  
    // Utiliza el dato obtenido en tus operaciones o asignaciones
    console.log(this.sprintId);
  }

  
}
