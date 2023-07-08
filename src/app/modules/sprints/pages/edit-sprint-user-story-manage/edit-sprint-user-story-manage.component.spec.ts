import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditSprintUserStoryManageComponent } from './edit-sprint-user-story-manage.component';

describe('EditSprintUserStoryManageComponent', () => {
  let component: EditSprintUserStoryManageComponent;
  let fixture: ComponentFixture<EditSprintUserStoryManageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditSprintUserStoryManageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditSprintUserStoryManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
