import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditEmployeeSprintComponent } from './edit-employee-sprint.component';

describe('EditEmployeeSprintComponent', () => {
  let component: EditEmployeeSprintComponent;
  let fixture: ComponentFixture<EditEmployeeSprintComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditEmployeeSprintComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditEmployeeSprintComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
