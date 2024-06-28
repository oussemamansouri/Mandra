import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthServiceService } from 'src/app/services/authService/auth-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  errmessage!: string;
  AuthUserSub!: Subscription; // Subscription to the authenticated user observable

  constructor(private router: Router, private authService: AuthServiceService) { }

  ngOnInit(): void {

    // Auto-login if there's a saved user
    this.authService.autoLogin();

    // Subscribe to the AuthenticatedUser$ observable to monitor authentication state
    this.AuthUserSub = this.authService.AuthenticatedUser$.subscribe({
      next: user => {
        // If a user is authenticated, navigate based on their role
        if (user) {
          this.navigateBasedOnRole(user);
        }
      }
    });


  }

  loginuser(formLogin: NgForm) {
    // Validate the form
    if (!formLogin.valid) {
      return;
    }

    const email = formLogin.value.email; // Get email from the form
    const password = formLogin.value.password; // Get password from the form

    // Call the login method from authService
    this.authService.login(email, password).then(userData => {
      if (userData) {
        this.navigateBasedOnRole(userData); // Navigate based on the user's role
      } else {
        this.router.navigate(['/']); // Navigate to the default route if user data is not available
      }
    }).catch(error => {
      // Handle login errors
      this.errmessage = error.message;
    });
  }

  navigateBasedOnRole(user: any) {
    if (user.role.name.includes('ROLE_Admin')) {
      this.router.navigate(['/admin']);
    } else if (user.role.name.includes('ROLE_Owner')) {
      this.router.navigate(['/owner']);
    } else if (user.role.name.includes('ROLE_Client')) {
      this.router.navigate(['/client']);
    } else {
      this.router.navigate(['/']);
    }
  }

  // Lifecycle hook that is called when the component is destroyed
  ngOnDestroy() {
    // Unsubscribe from the AuthenticatedUser$ observable to prevent memory leaks
    this.AuthUserSub.unsubscribe();
  }
}
