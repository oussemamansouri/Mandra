import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AddOwnerRoutingModule } from './add-owner-routing.module';
import { AddOwnerComponent } from './add-owner/add-owner.component';


@NgModule({
  declarations: [
    AddOwnerComponent
  ],
  imports: [
    CommonModule,
    AddOwnerRoutingModule
  ]
})
export class AddOwnerModule { }
