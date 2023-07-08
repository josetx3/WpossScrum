import { TestBed } from '@angular/core/testing';

import { userStoryService } from './user-story.service';

describe('ServiceService', () => {
  let service: userStoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(userStoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
