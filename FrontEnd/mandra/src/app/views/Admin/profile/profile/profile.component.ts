import { BaseURL } from 'src/app/Shared/base-url';
import { Component, Inject, OnInit, inject, OnDestroy } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AdminService } from 'src/app/services/apiServices/AdminService/admin.service';
import { AuthServiceService } from 'src/app/services/authService/auth-service.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {
  profile: any;
  AuthUserSub!: Subscription;
  baseURL!: string;
  img: any;
  old = "";
  new = "";
  repe = "";
  errmessage: any;
  secmessage: any;
  errmessagepass: any;
  secmessagepass: any;

  constructor(
    @Inject('BaseURL') private BaseURL: string,
    private adminService: AdminService,
    private router: Router,
    private authService: AuthServiceService
  ) { }

  ngOnInit(): void {
    this.AuthUserSub = this.authService.AuthenticatedUser$.subscribe({
      next: user => {
        if (user) {
          this.baseURL = this.BaseURL;
          this.adminService.getAdmin(user.id).subscribe({
            next: data => {
              this.profile = data;
            },
            error: (err: HttpErrorResponse) => {
              this.router.navigate(['/']);
            }
          });
        }
      }
    });
  }

  notthesame() {
    return this.new !== this.repe;
  }

  updateimage(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      const formData = new FormData();
      formData.append('image', file);
      this.adminService.updateAdminImage(this.profile.id, formData).subscribe(
        res => {
          this.ngOnInit();
        },
        err => console.error('Error updating image', err)
      );
    }
  }




  update(f: NgForm) {
    let updatedAdmin = f.value

    this.adminService.updateAdmin(this.profile.id, updatedAdmin).subscribe(info => {
      {
        this.secmessage = "Mise à jour terminée avec succès"
        this.ngOnInit()
      }
    }, (err: HttpErrorResponse) => {
      this.errmessage = err.error
    })
  }

  message() {
    this.errmessage = ''
    this.secmessage = ''

  }




  updatepassword(f: NgForm) {
     let upsateAdminPassword=f.value
      this.adminService.updateAdminPassword(this.profile.id,upsateAdminPassword).subscribe(info=>{
       this.router.navigate(['/admin'])
     },(err:HttpErrorResponse)=>{
       this.errmessagepass=err.error
       this.old=""

     })

  }

  // Lifecycle hook that is called when the component is destroyed
  ngOnDestroy() {
    // Unsubscribe from the AuthenticatedUser$ observable to prevent memory leaks
    this.AuthUserSub.unsubscribe();
  }

}
