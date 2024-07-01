import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientDetailsRoutingModule } from './client-details-routing.module';
import { ClientDetailsComponent } from './client-details/client-details.component';


@NgModule({
  declarations: [
    ClientDetailsComponent
  ],
  imports: [
    CommonModule,
    ClientDetailsRoutingModule
  ]
})
export class ClientDetailsModule { }
