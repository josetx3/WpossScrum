import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AreaInterface } from 'src/app/modules/area/pages/Interface/interface-area';
import { Team } from '../interface/team';
import { TeamsService } from '../service/teams.service';
import { AreaService } from 'src/app/modules/area/pages/service/area.service';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-team-add-manage',
  templateUrl: './team-add-manage.component.html',
  styleUrls: ['./team-add-manage.component.scss']
})
export class TeamAddManageComponent implements OnInit {
  manageTeamsForm: FormGroup = new FormGroup({
    areaId: new FormControl(null, [Validators.required]),
    teamName: new FormControl(null, [Validators.required,]),
    teamId: new FormControl(null),
  });

  areas: AreaInterface[]=[];
  team: Team[]=[];
  employees = [];
  area: any;
  teamId: string='';

constructor(
  private manageTeamsService: TeamsService,
    private areaService: AreaService,
    private dialog: MatDialog,
){}


ngOnInit(): void {
  this.getAllArea();
}

getAllArea() {
  this.areaService.getAllArea().subscribe(resp => {
    this.areas = resp;
  });
}

saveTeams(): void {
  if (this.manageTeamsForm.valid) {
    const data = {
      areaId: this.manageTeamsForm.get('areaId')?.value,
      teamName: this.manageTeamsForm.get('teamName')?.value,

    }
    this.manageTeamsService.saveTeam(data).subscribe(
      () => {
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Equipo creado',
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
        this.manageTeamsForm.reset();
        localStorage.removeItem('employees');
      },
    );
  }

}
}
