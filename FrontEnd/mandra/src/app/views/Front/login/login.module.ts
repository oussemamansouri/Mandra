import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import { FormsModule } from '@angular/forms';
import { AuthServiceService } from 'src/app/services/authService/auth-service.service';


@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    LoginRoutingModule,
    FormsModule
  ],
  providers: [AuthServiceService],
})
export class LoginModule { }
