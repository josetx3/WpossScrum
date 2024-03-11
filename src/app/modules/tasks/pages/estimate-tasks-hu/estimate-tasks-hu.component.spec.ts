import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EstimateTasksHuComponent } from './estimate-tasks-hu.component';

describe('EstimateTasksHuComponent', () => {
  let component: EstimateTasksHuComponent;
  let fixture: ComponentFixture<EstimateTasksHuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EstimateTasksHuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EstimateTasksHuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
