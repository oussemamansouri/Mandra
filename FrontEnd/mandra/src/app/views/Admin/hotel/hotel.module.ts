import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HotelRoutingModule } from './hotel-routing.module';
import { HotelComponent } from './hotel/hotel.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    HotelComponent
  ],
  imports: [
    CommonModule,
    HotelRoutingModule,
    FormsModule
  ]
})
export class HotelModule { }
