import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamTasksEditComponent } from './team-tasks-edit.component';

describe('TeamTasksEditComponent', () => {
  let component: TeamTasksEditComponent;
  let fixture: ComponentFixture<TeamTasksEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamTasksEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamTasksEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
