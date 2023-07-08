import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProyectSubprojectComponent } from './proyect-subproject.component';

describe('ProyectSubprojectComponent', () => {
  let component: ProyectSubprojectComponent;
  let fixture: ComponentFixture<ProyectSubprojectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProyectSubprojectComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProyectSubprojectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
