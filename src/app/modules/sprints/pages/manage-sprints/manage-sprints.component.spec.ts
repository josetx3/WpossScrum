import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageSprintsComponent } from './manage-sprints.component';

describe('ManageSprintsComponent', () => {
  let component: ManageSprintsComponent;
  let fixture: ComponentFixture<ManageSprintsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageSprintsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManageSprintsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
