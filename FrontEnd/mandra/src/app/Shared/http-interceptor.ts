import { HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable, take, switchMap, catchError, throwError } from "rxjs";
import { AuthServiceService } from "../services/authService/auth-service.service";

@Injectable()
export class HttpInterceptor implements HttpInterceptor {
  constructor(private authService: AuthServiceService, private router: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return this.authService.AuthenticatedUser$.pipe(
      take(1),
      switchMap(user => {
        const modifiedRequest = request.clone({
          // Add necessary headers or modify request if needed
        });

        return next.handle(modifiedRequest).pipe(
          catchError(err => {
            if (err instanceof HttpErrorResponse) {
              switch (err.status) {
                case 403:
                  this.router.navigate(['forbidden']);
                  break;
                case 401:
                  this.authService.logout();
                  break;
              }
            }
            return throwError(() => err);
          })
        );
      })
    );
  }
}
