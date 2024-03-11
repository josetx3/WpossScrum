import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GraphicBurndownchartComponent } from './graphic-burndownchart.component';

describe('GraphicBurndownchartComponent', () => {
  let component: GraphicBurndownchartComponent;
  let fixture: ComponentFixture<GraphicBurndownchartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GraphicBurndownchartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GraphicBurndownchartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
