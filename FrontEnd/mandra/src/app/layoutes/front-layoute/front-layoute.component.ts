import { DOCUMENT } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AdminService } from 'src/app/services/apiServices/AdminService/admin.service';
import { ClientService } from 'src/app/services/apiServices/ClientService/client.service';
import { OwnerService } from 'src/app/services/apiServices/OwnerService/owner.service';
import { AuthServiceService } from 'src/app/services/authService/auth-service.service';

@Component({
  selector: 'app-front-layoute',
  templateUrl: './front-layoute.component.html',
  styleUrls: ['./front-layoute.component.css']
})
export class FrontLayouteComponent {

  isSidebarOpen = false;
  user: any = null
  baseURL!: string
  errmessage!: string;
  isAdmin:boolean = false
  AuthUserSub!: Subscription; // Subscription to the authenticated user observable

  constructor(private router: Router, private authService: AuthServiceService,
    private adminService: AdminService, private clientService: ClientService,
    private ownerService: OwnerService, @Inject('BaseURL') private BaseURL: string) { }

  ngOnInit(): void {
    this.authService.autoLogin()
    // Subscribe to the AuthenticatedUser$ observable to monitor authentication state
    this.AuthUserSub = this.authService.AuthenticatedUser$.subscribe({
      next: data => {
        this.baseURL = this.BaseURL
        if(data){
        switch (data?.role.name) {
          case 'ROLE_Admin':
            this.adminService.getAdmin(data.id).subscribe(
              info =>{ this.user = info
              this.isAdmin = true}
            )
            break;
          case 'ROLE_Owner':
            this.ownerService.getOwnerById(data.id).subscribe(
              info => this.user = info
            )
            break;
          case 'ROLE_Client':
            this.clientService.getClientById(data.id).subscribe(
              info => this.user = info
            )
            break;
          default:
            this.router.navigate(['/signin'])
        }
      }
    }
    });
  }

  sidebarToggle() {
    this.isSidebarOpen = !this.isSidebarOpen;
  }

  logout() {
    this.authService.logout();
    this.AuthUserSub.unsubscribe();
    this.user = null
  }

  navigateprofile() {
    switch (this.user.role) {
      case 'Admin':
        this.router.navigate(['/admin/profile'])
        break;
      case 'Owner':
        this.router.navigate(['/owner'])
        break;
      case 'Client':
        this.router.navigate(['/client'])
        break;
      default:
        this.router.navigate(['/signin'])
    }

  }

  // Lifecycle hook that is called when the component is destroyed
  ngOnDestroy() {
    // Unsubscribe from the AuthenticatedUser$ observable to prevent memory leaks
    this.AuthUserSub.unsubscribe();
  }

}
