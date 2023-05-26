import { TestBed } from '@angular/core/testing';

import { ImprovementsService } from './improvements.service';

describe('ImprovementsService', () => {
  let service: ImprovementsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImprovementsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
