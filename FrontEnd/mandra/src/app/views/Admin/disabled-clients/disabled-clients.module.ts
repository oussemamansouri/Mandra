import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DisabledClientsRoutingModule } from './disabled-clients-routing.module';
import { DisabledClientsComponent } from './disabled-clients/disabled-clients.component';


@NgModule({
  declarations: [
    DisabledClientsComponent
  ],
  imports: [
    CommonModule,
    DisabledClientsRoutingModule
  ]
})
export class DisabledClientsModule { }
