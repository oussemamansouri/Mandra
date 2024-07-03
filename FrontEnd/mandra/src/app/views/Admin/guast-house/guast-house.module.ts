import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GuastHouseRoutingModule } from './guast-house-routing.module';
import { GuastHouseComponent } from './guast-house/guast-house.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    GuastHouseComponent
  ],
  imports: [
    CommonModule,
    GuastHouseRoutingModule,
    FormsModule
  ]
})
export class GuastHouseModule { }
