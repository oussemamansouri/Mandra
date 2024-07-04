import { OwnerService } from './../../services/apiServices/OwnerService/owner.service';
import { DOCUMENT } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AdminService } from 'src/app/services/apiServices/AdminService/admin.service';
import { AuthServiceService } from 'src/app/services/authService/auth-service.service';

@Component({
  selector: 'app-owner-layoute',
  templateUrl: './owner-layoute.component.html',
  styleUrls: ['./owner-layoute.component.css']
})
export class OwnerLayouteComponent implements OnInit, OnDestroy {

  user!: any;
  AuthUserSub!: Subscription;
  baseURL!: string;

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private router: Router,
    private authService: AuthServiceService,
    private OwnerService: OwnerService,
    @Inject('BaseURL') private BaseURL: string
  ) {}

  ngOnInit(): void {
    this.authService.autoLogin();

    this.AuthUserSub = this.authService.AuthenticatedUser$.subscribe({
      next: user => {
        if (user) {
          this.baseURL = this.BaseURL;
          this.OwnerService.getOwnerById(user.id).subscribe({
            next: data => {
              this.user = data;
            },
            error: (err: HttpErrorResponse) => {
              this.router.navigate(['/']);
            }
          });
        }
      }
    });
  }

  sidebarToggle() {
    this.document.body.classList.toggle('toggle-sidebar');
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  // Lifecycle hook that is called when the component is destroyed
  ngOnDestroy() {
    // Unsubscribe from the AuthenticatedUser$ observable to prevent memory leaks
    this.AuthUserSub.unsubscribe();
  }

}
