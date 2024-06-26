import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { map, take } from 'rxjs';
import { AuthServiceService } from 'src/app/services/authService/auth-service.service';

export const loginAdminGuardGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);
  const authService = inject(AuthServiceService);

  return authService.AuthenticatedUser$.pipe(
    take(1), // take the first one and then unsubscribe automatically
    map(user => {
      // check if route is restricted by role
      //const  roles  = route.data['roles'];
      const { roles } = route.data;
      if(user && user.role && roles.includes(user.role.name = "Admin")) {
       return true;
      }
      if(user) {
       return  router.createUrlTree(['/forbidden']);
      }
      return  router.createUrlTree(['/signin']);
    })
  )
};
