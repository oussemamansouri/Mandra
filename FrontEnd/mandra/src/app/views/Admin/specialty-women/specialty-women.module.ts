import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SpecialtyWomenRoutingModule } from './specialty-women-routing.module';
import { SpecialtyWomenComponent } from './specialty-women/specialty-women.component';


@NgModule({
  declarations: [
    SpecialtyWomenComponent
  ],
  imports: [
    CommonModule,
    SpecialtyWomenRoutingModule
  ]
})
export class SpecialtyWomenModule { }
