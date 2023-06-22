import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubprojectUserStoryComponent } from './subproject-user-story.component';

describe('SubprojectUserStoryComponent', () => {
  let component: SubprojectUserStoryComponent;
  let fixture: ComponentFixture<SubprojectUserStoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubprojectUserStoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubprojectUserStoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
