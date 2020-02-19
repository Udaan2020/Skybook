import { TestBed } from '@angular/core/testing';

import { BookingdetailsService } from './bookingdetails.service';

describe('BookingdetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BookingdetailsService = TestBed.get(BookingdetailsService);
    expect(service).toBeTruthy();
  });
});
