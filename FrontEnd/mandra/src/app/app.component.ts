import { Component } from '@angular/core';
import { AuthServiceService } from './services/authService/auth-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'mandra';
  // Indicates if the user is authenticated
  isAuth: boolean = false;

  // Shows admin board if user has admin role
  showAdminBoard = false;

  // Injecting AuthService and Router into the component
  constructor(private authService: AuthServiceService) { }

  // Initialization logic
  ngOnInit(): void {
    // Attempt to auto-login the user
    // this.authService.autoLogin();

}

}
