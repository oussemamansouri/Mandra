import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { loginAdminGuardGuard } from './login-admin-guard.guard';

describe('loginAdminGuardGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => loginAdminGuardGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
