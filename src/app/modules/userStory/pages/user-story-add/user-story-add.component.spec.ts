import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserStoryAddComponent } from './user-story-add.component';

describe('UserStoryAddComponent', () => {
  let component: UserStoryAddComponent;
  let fixture: ComponentFixture<UserStoryAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserStoryAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserStoryAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
