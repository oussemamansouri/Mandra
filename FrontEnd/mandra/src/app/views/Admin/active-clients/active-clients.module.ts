import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ActiveClientsRoutingModule } from './active-clients-routing.module';
import { ActiveClientsComponent } from './active-clients/active-clients.component';


@NgModule({
  declarations: [
    ActiveClientsComponent
  ],
  imports: [
    CommonModule,
    ActiveClientsRoutingModule
  ]
})
export class ActiveClientsModule { }
