import { TestBed } from '@angular/core/testing';

import { TeamsTasksService } from './teams-tasks.service';

describe('TeamsTasksService', () => {
  let service: TeamsTasksService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TeamsTasksService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
