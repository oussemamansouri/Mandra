import { DOCUMENT } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthServiceService } from 'src/app/services/authService/auth-service.service';

@Component({
  selector: 'app-front-layoute',
  templateUrl: './front-layoute.component.html',
  styleUrls: ['./front-layoute.component.css']
})
export class FrontLayouteComponent {

  isSidebarOpen = false;
  data:any
  imagepath:any='http://localhost:8080/'
  errmessage!: string;
  AuthUserSub!: Subscription; // Subscription to the authenticated user observable

  constructor(private router:Router, private authService:AuthServiceService) {}

  ngOnInit(): void {
     // Subscribe to the AuthenticatedUser$ observable to monitor authentication state
     this.AuthUserSub = this.authService.AuthenticatedUser$.subscribe({
      next: user => {
        if (user) {
          this.data = user;
          console.log(this.data)
        }
      }
    });
  }

  sidebarToggle() {
    this.isSidebarOpen = !this.isSidebarOpen;
  }

  logout(){
  this.authService.logout();
    this.ngOnInit();
    this.router.navigate(['/']);
  }

  navigateprofile(){
    switch(this.data.role.name) {
      case 'ROLE_Admin':
        this.router.navigate(['/admin'])
        break;
      case 'ROLE_Owner':
        this.router.navigate(['/owner'])
        break;
        case 'ROLE_Client':
          this.router.navigate(['/client'])
          break;
      default:
        this.router.navigate(['/login'])
    }

  }

  // Lifecycle hook that is called when the component is destroyed
  ngOnDestroy() {
    // Unsubscribe from the AuthenticatedUser$ observable to prevent memory leaks
    this.AuthUserSub.unsubscribe();
  }

}
