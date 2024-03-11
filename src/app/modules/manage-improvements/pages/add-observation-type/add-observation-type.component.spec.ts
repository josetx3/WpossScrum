import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddObservationTypeComponent } from './add-observation-type.component';

describe('AddObservationTypeComponent', () => {
  let component: AddObservationTypeComponent;
  let fixture: ComponentFixture<AddObservationTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddObservationTypeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddObservationTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
