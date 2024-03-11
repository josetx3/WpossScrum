import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUserStorySprintComponent } from './add-user-story-sprint.component';

describe('AddUserStorySprintComponent', () => {
  let component: AddUserStorySprintComponent;
  let fixture: ComponentFixture<AddUserStorySprintComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddUserStorySprintComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddUserStorySprintComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
