import { TestBed } from '@angular/core/testing';

import { FlightResolverService } from './flight-resolver.service';

describe('FlightResolverService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FlightResolverService = TestBed.get(FlightResolverService);
    expect(service).toBeTruthy();
  });
});
