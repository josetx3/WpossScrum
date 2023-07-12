import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditObservationTypeComponent } from './edit-observation-type.component';

describe('EditObservationTypeComponent', () => {
  let component: EditObservationTypeComponent;
  let fixture: ComponentFixture<EditObservationTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditObservationTypeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditObservationTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
