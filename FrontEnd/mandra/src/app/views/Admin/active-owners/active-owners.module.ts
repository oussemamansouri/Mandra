import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ActiveOwnersRoutingModule } from './active-owners-routing.module';
import { ActiveOwnersComponent } from './active-owners/active-owners.component';


@NgModule({
  declarations: [
    ActiveOwnersComponent
  ],
  imports: [
    CommonModule,
    ActiveOwnersRoutingModule
  ]
})
export class ActiveOwnersModule { }
