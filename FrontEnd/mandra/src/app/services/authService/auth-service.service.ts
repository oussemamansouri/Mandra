import { Inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from 'src/app/Shared/user';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  AuthenticatedUser$ = new BehaviorSubject<User | null>(null); // BehaviorSubject to hold the authenticated user

  constructor(
    private http: HttpClient, // Inject HttpClient for HTTP requests
    private router: Router, // Inject Router for navigation
    @Inject('BaseURL') private baseURL: any, // Inject baseURL for API endpoints
  ) { }
}
