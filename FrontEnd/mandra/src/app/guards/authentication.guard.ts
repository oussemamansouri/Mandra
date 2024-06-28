import { inject } from '@angular/core';
import { CanActivateFn, Router, ActivatedRouteSnapshot } from '@angular/router';
import { AuthServiceService } from '../services/authService/auth-service.service';
import { map, take } from 'rxjs/operators';

export const authenticationGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state) => {
  const router = inject(Router);
  const authService = inject(AuthServiceService);

  // console.log('Guard activated');

  authService.autoLogin();

  return authService.AuthenticatedUser$.pipe(
    take(1), // take the first one and then unsubscribe automatically
    map(user => {
      // console.log('User from AuthService:', user);
      // check if route is restricted by role
      const roles = route.data['roles'] as string[];
      // console.log('Required roles:', roles);

      if (user && user.role && roles.includes(user.role.name)) {
        // console.log('User has required role, allowing access');
        return true; // User has the required role
      }

      if (user) {
        // console.log('User authenticated but does not have required role, redirecting to /forbidden');
        return router.createUrlTree(['/forbidden']); // User is authenticated but doesn't have the required role
      }

      // console.log('User not authenticated, redirecting to /signin');
      return router.createUrlTree(['/signin']); // User is not authenticated
    })
  );
};
