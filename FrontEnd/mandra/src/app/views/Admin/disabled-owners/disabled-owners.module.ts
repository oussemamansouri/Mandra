import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DisabledOwnersRoutingModule } from './disabled-owners-routing.module';
import { DisabledOwnersComponent } from './disabled-owners/disabled-owners.component';


@NgModule({
  declarations: [
    DisabledOwnersComponent
  ],
  imports: [
    CommonModule,
    DisabledOwnersRoutingModule
  ]
})
export class DisabledOwnersModule { }
