import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditEstimateTasksHuComponent } from './edit-estimate-tasks-hu.component';

describe('EditEstimateTasksHuComponent', () => {
  let component: EditEstimateTasksHuComponent;
  let fixture: ComponentFixture<EditEstimateTasksHuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditEstimateTasksHuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditEstimateTasksHuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
