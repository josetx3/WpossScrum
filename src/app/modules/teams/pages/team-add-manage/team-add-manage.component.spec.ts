import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamAddManageComponent } from './team-add-manage.component';

describe('TeamAddManageComponent', () => {
  let component: TeamAddManageComponent;
  let fixture: ComponentFixture<TeamAddManageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamAddManageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamAddManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
