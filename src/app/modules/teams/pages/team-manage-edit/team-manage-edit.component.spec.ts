import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamManageEditComponent } from './team-manage-edit.component';

describe('TeamManageEditComponent', () => {
  let component: TeamManageEditComponent;
  let fixture: ComponentFixture<TeamManageEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamManageEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamManageEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
