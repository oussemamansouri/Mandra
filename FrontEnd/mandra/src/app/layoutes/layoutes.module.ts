import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminLayouteComponent } from './admin-layoute/admin-layoute.component';
import { ClientLayouteComponent } from './client-layoute/client-layoute.component';
import { OwnerLayouteComponent } from './owner-layoute/owner-layoute.component';
import { FrontLayouteComponent } from './front-layoute/front-layoute.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AdminLayouteComponent,
    ClientLayouteComponent,
    OwnerLayouteComponent,
    FrontLayouteComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ]
})
export class LayoutesModule { }
