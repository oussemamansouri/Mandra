import { DOCUMENT } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-front-layoute',
  templateUrl: './front-layoute.component.html',
  styleUrls: ['./front-layoute.component.css']
})
export class FrontLayouteComponent {

  isSidebarOpen = false;
  data:any
  imagepath:any='http://localhost:8080/'


  constructor(@Inject(DOCUMENT) private document: Document,private route:Router) {}

  ngOnInit(): void {
    // const token = localStorage.getItem('token');
    // if (token) {
    //   const decodedToken = this.helper.decodeToken(token);
    //   if (decodedToken.role === 'client' ) {
    //     decodedToken.role = 'Apprenant';
    //   }
    //   this.data = decodedToken;
    // }else{this.data=''}
  }

  sidebarToggle() {
    this.isSidebarOpen = !this.isSidebarOpen;
  }

  logout(){
    localStorage.removeItem('token')
    this.route.navigate(['/'])
    this.ngOnInit()
       }

  navigateprofile(){
    switch(this.data.role) {
      case 'admin':
        this.route.navigate(['/admin'])
        break;
      case 'client':
        this.route.navigate(['/client'])
        break;
        case 'centre':
          this.route.navigate(['/centre'])
          break;
      default:
        this.route.navigate(['/login'])
    }

  }



  scrollTop() {
    window.scroll({
      top: 0,
      left: 0,
      behavior: 'smooth'
    });

  }

}
