import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SpecialtyWomensRoutingModule } from './specialty-womens-routing.module';
import { SpecialtyWomensComponent } from './specialty-womens/specialty-womens.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    SpecialtyWomensComponent
  ],
  imports: [
    CommonModule,
    SpecialtyWomensRoutingModule,
    FormsModule
  ]
})
export class SpecialtyWomensModule { }
