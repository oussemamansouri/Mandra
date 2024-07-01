import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GuastHouseRoutingModule } from './guast-house-routing.module';
import { GuastHouseComponent } from './guast-house/guast-house.component';


@NgModule({
  declarations: [
    GuastHouseComponent
  ],
  imports: [
    CommonModule,
    GuastHouseRoutingModule
  ]
})
export class GuastHouseModule { }
