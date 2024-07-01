import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AddClientRoutingModule } from './add-client-routing.module';
import { AddClientComponent } from './add-client/add-client.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AddClientComponent
  ],
  imports: [
    CommonModule,
    AddClientRoutingModule,
    FormsModule
  ]
})
export class AddClientModule { }
