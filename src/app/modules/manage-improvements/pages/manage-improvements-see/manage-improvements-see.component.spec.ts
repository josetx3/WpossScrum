import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageImprovementsSeeComponent } from './manage-improvements-see.component';

describe('ManageImprovementsSeeComponent', () => {
  let component: ManageImprovementsSeeComponent;
  let fixture: ComponentFixture<ManageImprovementsSeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageImprovementsSeeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManageImprovementsSeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
