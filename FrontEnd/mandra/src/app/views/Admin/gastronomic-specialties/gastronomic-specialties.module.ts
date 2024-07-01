import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GastronomicSpecialtiesRoutingModule } from './gastronomic-specialties-routing.module';
import { GastronomicSpecialtiesComponent } from './gastronomic-specialties/gastronomic-specialties.component';


@NgModule({
  declarations: [
    GastronomicSpecialtiesComponent
  ],
  imports: [
    CommonModule,
    GastronomicSpecialtiesRoutingModule
  ]
})
export class GastronomicSpecialtiesModule { }
