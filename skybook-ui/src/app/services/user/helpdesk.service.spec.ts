import { TestBed } from '@angular/core/testing';

import { HelpdeskService } from './helpdesk.service';

describe('HelpdeskService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HelpdeskService = TestBed.get(HelpdeskService);
    expect(service).toBeTruthy();
  });
});
