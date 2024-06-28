import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent, HttpErrorResponse,
} from '@angular/common/http';
import { catchError, concatMap, Observable, switchMap, take, throwError } from 'rxjs';
import { Router } from "@angular/router";
import { AuthServiceService } from '../services/authService/auth-service.service';

@Injectable()
export class HttpInterceptor implements HttpInterceptor {

  constructor(private authService: AuthServiceService, private router: Router) {
  }
 // Intercept method to handle HTTP requests
 intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
  // Retrieve the current authenticated user from AuthService
  return this.authService.AuthenticatedUser$.pipe(
    take(1), // Take only the first emitted value from the Observable
    switchMap(user => {
      // If the user is not authenticated, pass the request as is
      if (!user) {
        return next.handle(request);
      }
      // Otherwise, pass the request and intercept any errors
      return next.handle(request).pipe(
        catchError(err => {
          // Check if the error is an HTTP error response
          if (err instanceof HttpErrorResponse) {
            switch (err.status) {
              case 403:
                // Navigate to the "forbidden" page if the error status is 403
                this.router.navigate(['forbidden']);
                break;
              case 401: // Handle 401 unauthorized error: try to refresh JWT
                // Log out the user if the error status is 401
                this.authService.logout();
                break;
            }
          }
          // Re-throw the error after handling it
          return throwError(() => err);
        })
      );
    })
  );
}
}
