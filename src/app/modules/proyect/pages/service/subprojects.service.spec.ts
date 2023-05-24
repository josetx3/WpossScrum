import { TestBed } from '@angular/core/testing';

import { SubprojectService } from './subproject.service';

describe('SubprojectsService', () => {
  let service: SubprojectService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SubprojectService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
