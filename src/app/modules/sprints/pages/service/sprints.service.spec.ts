import { TestBed } from '@angular/core/testing';

import { SprintsService } from './sprints.service';

describe('SprintsService', () => {
  let service: SprintsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SprintsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
