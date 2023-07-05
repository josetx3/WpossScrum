import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SprintUserStoryManageComponent } from './sprint-user-story-manage.component';

describe('SprintUserStoryManageComponent', () => {
  let component: SprintUserStoryManageComponent;
  let fixture: ComponentFixture<SprintUserStoryManageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SprintUserStoryManageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SprintUserStoryManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
