import { Inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(
    private http: HttpClient, // Inject HttpClient for HTTP requests
    private router: Router, // Inject Router for navigation
    @Inject('BaseURL') private baseURL: any, // Inject baseURL for API endpoints
  ) { }
}
