import { TestBed } from '@angular/core/testing';

import { AirlinesService } from './airlines.service';

describe('AirlinesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AirlinesService = TestBed.get(AirlinesService);
    expect(service).toBeTruthy();
  });
});
