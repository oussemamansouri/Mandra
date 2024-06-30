import { Component, OnInit, Inject, OnDestroy } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/authService/auth-service.service';
import { Subscription } from 'rxjs';
import { AdminService } from 'src/app/services/apiServices/AdminService/admin.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-admin-layoute',
  templateUrl: './admin-layoute.component.html',
  styleUrls: ['./admin-layoute.component.css']
})
export class AdminLayouteComponent implements OnInit, OnDestroy {

  user!: any;
  AuthUserSub!: Subscription;
  baseURL!: string;

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private router: Router,
    private authService: AuthServiceService,
    private adminService: AdminService,
    @Inject('BaseURL') private BaseURL: string
  ) {}

  ngOnInit(): void {
    this.authService.autoLogin();

    this.AuthUserSub = this.authService.AuthenticatedUser$.subscribe({
      next: user => {
        if (user) {
          this.baseURL = this.BaseURL;
          this.adminService.getAdmin(user.id).subscribe({
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
