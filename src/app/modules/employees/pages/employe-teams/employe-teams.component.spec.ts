import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeTeamsComponent } from './employe-teams.component';

describe('EmployeTeamsComponent', () => {
  let component: EmployeTeamsComponent;
  let fixture: ComponentFixture<EmployeTeamsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeTeamsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeTeamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
