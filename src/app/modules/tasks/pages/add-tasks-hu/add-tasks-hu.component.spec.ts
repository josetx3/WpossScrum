import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTasksHuComponent } from './add-tasks-hu.component';

describe('AddTasksHuComponent', () => {
  let component: AddTasksHuComponent;
  let fixture: ComponentFixture<AddTasksHuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddTasksHuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddTasksHuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
