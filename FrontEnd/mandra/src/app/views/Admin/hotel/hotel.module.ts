import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HotelRoutingModule } from './hotel-routing.module';
import { HotelComponent } from './hotel/hotel.component';
import { FormsModule } from '@angular/forms';
import { BooleanToTextPipe } from 'src/app/Shared/boolean-to-text.pipe';


@NgModule({
  declarations: [
    HotelComponent,
    BooleanToTextPipe
  ],
  imports: [
    CommonModule,
    HotelRoutingModule,
    FormsModule
  ]
})
export class HotelModule { }
