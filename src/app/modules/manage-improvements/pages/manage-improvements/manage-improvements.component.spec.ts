import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageImprovementsComponent } from './manage-improvements.component';

describe('ManageImprovementsComponent', () => {
  let component: ManageImprovementsComponent;
  let fixture: ComponentFixture<ManageImprovementsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageImprovementsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManageImprovementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
