import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProyectAddComponent } from './proyect-add.component';

describe('ProyectAddComponent', () => {
  let component: ProyectAddComponent;
  let fixture: ComponentFixture<ProyectAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProyectAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProyectAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
