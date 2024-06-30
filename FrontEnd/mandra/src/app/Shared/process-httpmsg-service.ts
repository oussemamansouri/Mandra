import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProcessHttpmsgService {

  constructor() { }

  public handleError(error: HttpErrorResponse | any) {
    let errMsg: string;

    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errMsg = `Client-side error: ${error.error.message}`;
    } else {
      // Server-side error
      const serverError = error.error as ErrorResponse;
      const errorMessage = serverError.message || 'An error occurred, please try again later.';
      const errorDetails = serverError.details || '';

      errMsg = `Server-side error: ${error.status} ${error.statusText || ''} - ${errorMessage} ${errorDetails}`;
    }

    return throwError(() => new Error(errMsg));
  }
}

// Define the ErrorResponse interface for TypeScript
interface ErrorResponse {
  message: string;
  details: string;
}
