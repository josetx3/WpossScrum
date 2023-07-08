import { TestBed } from '@angular/core/testing';

import { UserStoyStatusService } from './user-stoy-status.service';

describe('UserStoyStatusService', () => {
  let service: UserStoyStatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserStoyStatusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
