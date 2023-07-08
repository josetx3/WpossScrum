import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProyectEditComponent } from './proyect-edit.component';

describe('ProyectEditComponent', () => {
  let component: ProyectEditComponent;
  let fixture: ComponentFixture<ProyectEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProyectEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProyectEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
