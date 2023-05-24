import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamTasksComponent } from './team-tasks.component';

describe('TeamTasksComponent', () => {
  let component: TeamTasksComponent;
  let fixture: ComponentFixture<TeamTasksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamTasksComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamTasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
