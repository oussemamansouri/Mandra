import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegistreClientRoutingModule } from './registre-client-routing.module';
import { RegistreClientComponent } from './registre-client/registre-client.component';


@NgModule({
  declarations: [
    RegistreClientComponent
  ],
  imports: [
    CommonModule,
    RegistreClientRoutingModule
  ]
})
export class RegistreClientModule { }
