import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserStoryManagementComponent } from './user-story-management.component';

describe('UserStoryManagementComponent', () => {
  let component: UserStoryManagementComponent;
  let fixture: ComponentFixture<UserStoryManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserStoryManagementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserStoryManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
