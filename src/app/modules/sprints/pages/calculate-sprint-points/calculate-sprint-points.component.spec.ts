import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalculateSprintPointsComponent } from './calculate-sprint-points.component';

describe('CalculateSprintPointsComponent', () => {
  let component: CalculateSprintPointsComponent;
  let fixture: ComponentFixture<CalculateSprintPointsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalculateSprintPointsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CalculateSprintPointsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
