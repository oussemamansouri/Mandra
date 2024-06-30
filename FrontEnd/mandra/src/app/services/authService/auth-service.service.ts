import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Inject, Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, catchError, throwError, tap } from "rxjs";
import { AuthResponseData } from "src/app/Shared/auth-response-data";
import { User } from "src/app/Shared/user";
import { StorageServiceService } from "../storageService/storage-service.service";

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  AuthenticatedUser$ = new BehaviorSubject<User | null>(null);

  constructor(
    private http: HttpClient,
    private router: Router,
    private storageService: StorageServiceService,
    @Inject('BaseURL') private baseURL: any,
  ) { }

  login(email: string, password: string) {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + window.btoa(email + ':' + password)
      }),
      withCredentials: true
    };

    return this.http.post<AuthResponseData>(this.baseURL + '/auth/signin', null, httpOptions).pipe(
      catchError(err => {
        let errorMessage = 'An unknown error occurred!';
        errorMessage = 'The email or password you entered is incorrect.';
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
        this.storageService.saveUser(extractedUser);
        this.AuthenticatedUser$.next(extractedUser);
      })
    ).toPromise();
  }

  autoLogin() {
    const userData = this.storageService.getSavedUser();
    if (userData) {
      this.AuthenticatedUser$.next(userData);
    }
  }

  logout() {
    this.http.post(this.baseURL + '/auth/signout', {}, { withCredentials: true }).subscribe(() => {
      this.storageService.clean();
      this.AuthenticatedUser$.next(null);
      this.router.navigate(['/signin']);
    });
  }
}
