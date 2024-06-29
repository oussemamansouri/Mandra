import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AddOwnerRoutingModule } from './add-owner-routing.module';
import { AddOwnerComponent } from './add-owner/add-owner.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AddOwnerComponent
  ],
  imports: [
    CommonModule,
    AddOwnerRoutingModule,
    FormsModule
  ]
})
export class AddOwnerModule { }
