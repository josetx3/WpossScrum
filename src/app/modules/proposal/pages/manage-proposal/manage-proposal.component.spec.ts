import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageProposalComponent } from './manage-proposal.component';

describe('ManageProposalComponent', () => {
  let component: ManageProposalComponent;
  let fixture: ComponentFixture<ManageProposalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageProposalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManageProposalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
