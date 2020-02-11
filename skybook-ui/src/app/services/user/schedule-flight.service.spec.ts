import { TestBed } from '@angular/core/testing';

import { ScheduleFlightService } from './schedule-flight.service';

describe('ScheduleFlightService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ScheduleFlightService = TestBed.get(ScheduleFlightService);
    expect(service).toBeTruthy();
  });
});
