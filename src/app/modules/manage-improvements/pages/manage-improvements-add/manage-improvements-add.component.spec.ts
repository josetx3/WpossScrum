import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageImprovementsAddComponent } from './manage-improvements-add.component';

describe('ManageImprovementsAddComponent', () => {
  let component: ManageImprovementsAddComponent;
  let fixture: ComponentFixture<ManageImprovementsAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageImprovementsAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManageImprovementsAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
