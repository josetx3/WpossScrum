import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamEmployeesListComponent } from './team-employees-list.component';

describe('TeamEmployeesListComponent', () => {
  let component: TeamEmployeesListComponent;
  let fixture: ComponentFixture<TeamEmployeesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamEmployeesListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamEmployeesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
