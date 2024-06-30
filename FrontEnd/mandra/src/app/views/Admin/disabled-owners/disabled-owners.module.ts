import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DisabledOwnersRoutingModule } from './disabled-owners-routing.module';
import { DisabledOwnersComponent } from './disabled-owners/disabled-owners.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    DisabledOwnersComponent
  ],
  imports: [
    CommonModule,
    DisabledOwnersRoutingModule,
    FormsModule
  ]
})
export class DisabledOwnersModule { }
