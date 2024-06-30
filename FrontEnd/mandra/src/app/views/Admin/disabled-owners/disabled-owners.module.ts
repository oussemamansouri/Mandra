import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DisabledOwnersRoutingModule } from './disabled-owners-routing.module';
import { DisabledOwnersComponent } from './disabled-owners/disabled-owners.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    DisabledOwnersComponent
  ],
  imports: [
    CommonModule,
    DisabledOwnersRoutingModule,
    FormsModule,
    HttpClientModule
  ]
})
export class DisabledOwnersModule { }
