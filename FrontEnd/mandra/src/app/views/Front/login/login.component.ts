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
export class LoginComponent implements OnInit, OnDestroy{

  errmessage!: string;
  AuthUserSub!: Subscription; // Subscription to the authenticated user observable

  constructor(private router: Router, private authService: AuthServiceService) { }

  ngOnInit(): void {
    // Subscribe to the AuthenticatedUser$ observable to monitor authentication state
    this.AuthUserSub = this.authService.AuthenticatedUser$.subscribe({
      next: user => {
        // If a user is authenticated, navigate to the home page
        if (user) {
          this.router.navigate(['/']);
        }
      }
    });
    // const user:any = window.localStorage.getItem('authenticated-user')
    // if(user){
    //   this.router.navigate(['/']);
    // }
  }

  loginuser(formLogin: NgForm) {
    // Validate the form
    if (!formLogin.valid) {
      return;
    }

    const email = formLogin.value.email; // Get email from the form
    const password = formLogin.value.password; // Get password from the form

    // Call the login method from AuthService
    this.authService.login(email, password).subscribe({
      next: userData => {
        if(userData.roles.find(role => role.includes('ROLE_Admin'))){
          this.router.navigate(['/admin']);
        }else if(userData.roles.find(role => role.includes('ROLE_Owner'))){
          this.router.navigate(['/owner']);
        }else if(userData.roles.find(role => role.includes('ROLE_Client'))){
        this.router.navigate(['/client']);
        }else{
          this.router.navigate(['/']);
        }
      },
      error: err => {
        // On error, set the error message and log it to the console
        this.errmessage = err;
      }
    });
  }

  // Lifecycle hook that is called when the component is destroyed
  ngOnDestroy() {
    // Unsubscribe from the AuthenticatedUser$ observable to prevent memory leaks
    this.AuthUserSub.unsubscribe();
  }
}
