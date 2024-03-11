import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StateUserStoryAddComponent } from './state-user-story-add.component';

describe('StateUserStoryAddComponent', () => {
  let component: StateUserStoryAddComponent;
  let fixture: ComponentFixture<StateUserStoryAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StateUserStoryAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StateUserStoryAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
