import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OwnerDetailsRoutingModule } from './owner-details-routing.module';
import { OwnerDetailsComponent } from './owner-details/owner-details.component';


@NgModule({
  declarations: [
    OwnerDetailsComponent
  ],
  imports: [
    CommonModule,
    OwnerDetailsRoutingModule
  ]
})
export class OwnerDetailsModule { }
