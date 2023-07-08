import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScoreSprintDaysComponent } from './score-sprint-days.component';

describe('ScoreSprintDaysComponent', () => {
  let component: ScoreSprintDaysComponent;
  let fixture: ComponentFixture<ScoreSprintDaysComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScoreSprintDaysComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScoreSprintDaysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
