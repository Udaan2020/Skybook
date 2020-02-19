import { TestBed } from '@angular/core/testing';

import { ConfirmEqualValidatorDirective } from './confirm-equal-password.service';

describe('ConfirmEqualPasswordService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ConfirmEqualValidatorDirective = TestBed.get(ConfirmEqualValidatorDirective);
    expect(service).toBeTruthy();
  });
});
