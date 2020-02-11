import { TestBed } from '@angular/core/testing';

import { AdminGuardService } from './admin-authentication-guard.service.ts.service';

describe('AdminAuthenticationGuard.Service.TsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AdminGuardService = TestBed.get(AdminGuardService);
    expect(service).toBeTruthy();
  });
});
