import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProyectComponent } from './proyect.component';

describe('ProyectComponent', () => {
  let component: ProyectComponent;
  let fixture: ComponentFixture<ProyectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProyectComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProyectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
