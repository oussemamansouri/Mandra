import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UpdateOwnerRoutingModule } from './update-owner-routing.module';
import { UpdateOwnerComponent } from './update-owner/update-owner.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    UpdateOwnerComponent
  ],
  imports: [
    CommonModule,
    UpdateOwnerRoutingModule,
    FormsModule
  ]
})
export class UpdateOwnerModule { }
