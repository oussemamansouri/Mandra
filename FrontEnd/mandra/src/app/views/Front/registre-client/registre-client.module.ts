import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegistreClientRoutingModule } from './registre-client-routing.module';
import { RegistreClientComponent } from './registre-client/registre-client.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    RegistreClientComponent
  ],
  imports: [
    CommonModule,
    RegistreClientRoutingModule,
    FormsModule
  ]
})
export class RegistreClientModule { }
