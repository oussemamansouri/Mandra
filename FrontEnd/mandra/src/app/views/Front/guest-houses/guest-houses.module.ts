import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GuestHousesRoutingModule } from './guest-houses-routing.module';
import { GuestHousesComponent } from './guest-houses/guest-houses.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    GuestHousesComponent
  ],
  imports: [
    CommonModule,
    GuestHousesRoutingModule,
    FormsModule
  ]
})
export class GuestHousesModule { }
