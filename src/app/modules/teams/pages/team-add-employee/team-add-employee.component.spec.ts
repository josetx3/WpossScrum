import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamAddEmployeeComponent } from './team-add-employee.component';

describe('TeamAddEmployeeComponent', () => {
  let component: TeamAddEmployeeComponent;
  let fixture: ComponentFixture<TeamAddEmployeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamAddEmployeeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamAddEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
