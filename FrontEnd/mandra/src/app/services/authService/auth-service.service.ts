import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from 'src/app/Shared/user';
import { BehaviorSubject, catchError, tap, throwError } from 'rxjs';
import { AuthResponseData } from 'src/app/Shared/auth-response-data';
import { StorageServiceService } from '../storageService/storage-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  AuthenticatedUser$ = new BehaviorSubject<User | null>(null); // BehaviorSubject to hold the authenticated user

  constructor(
    private http: HttpClient, // Inject HttpClient for HTTP requests
    private router: Router, // Inject Router for navigation
    private storageService: StorageServiceService, // Inject StorageService for local storage operations
    @Inject('BaseURL') private baseURL: any, // Inject baseURL for API endpoints
  ) { }


  // Method to log in a user with email and password
  login(email: string, password: string) {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json', // Set content type to JSON
        'Authorization': 'Basic ' + window.btoa(email + ':' + password) // Add basic auth header
      }),
      withCredentials: true // Include credentials (cookies) in the request
    };

    return this.http.post<AuthResponseData>(this.baseURL + '/auth/signin', null, httpOptions).pipe(
      catchError(err => {
        let errorMessage = 'An unknown error occurred!';
        errorMessage = 'L’adresse e-mail ou le mot de passe que vous avez saisi est invalide';
        return throwError(() => new Error(errorMessage));
      }),
      tap(user => {
        const extractedUser: User = {
          email: user.email,
          id: user.id,
          role: {
            name: user.roles.find(role => role.includes('ROLE')) || '',
            permissions: user.roles.filter(permission => !permission.includes('ROLE'))
          }
        };
        this.storageService.saveUser(extractedUser); // Save user to local storage
        this.AuthenticatedUser$.next(extractedUser); // Update BehaviorSubject with authenticated user
      })
    ).toPromise(); // Convert Observable to Promise to ensure completion before returning
  }

    // Method to automatically log in a user if they are already authenticated
    autoLogin() {
      const userData = this.storageService.getSavedUser();
      if (!userData) {
        return;
      }
      this.AuthenticatedUser$.next(userData); // Update BehaviorSubject with authenticated user
    }



    // Method to log out the current user
    logout() {
      this.http.request('post', this.baseURL + '/auth/signout', {
        withCredentials: true // Include credentials (cookies) in the request
      }).subscribe({
        next: () => {
          this.storageService.clean(); // Clear local storage
          this.AuthenticatedUser$.next(null); // Reset authenticated user
          this.router.navigate(['/signin']); // Navigate to the sign-in page
        }
      });
    }

}
