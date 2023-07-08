import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardSeeComponent } from './board-see.component';

describe('BoardSeeComponent', () => {
  let component: BoardSeeComponent;
  let fixture: ComponentFixture<BoardSeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoardSeeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BoardSeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
