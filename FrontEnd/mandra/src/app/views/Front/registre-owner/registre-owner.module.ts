import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegistreOwnerRoutingModule } from './registre-owner-routing.module';
import { RegistreOwnerComponent } from './registre-owner/registre-owner.component';


@NgModule({
  declarations: [
    RegistreOwnerComponent
  ],
  imports: [
    CommonModule,
    RegistreOwnerRoutingModule
  ]
})
export class RegistreOwnerModule { }
