import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegistreOwnerRoutingModule } from './registre-owner-routing.module';
import { RegistreOwnerComponent } from './registre-owner/registre-owner.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    RegistreOwnerComponent
  ],
  imports: [
    CommonModule,
    RegistreOwnerRoutingModule,
    FormsModule
  ]
})
export class RegistreOwnerModule { }
