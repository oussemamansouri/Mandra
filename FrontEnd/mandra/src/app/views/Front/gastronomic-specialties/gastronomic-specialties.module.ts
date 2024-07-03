import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GastronomicSpecialtiesRoutingModule } from './gastronomic-specialties-routing.module';
import { GastronomicSpecialtiesComponent } from './gastronomic-specialties/gastronomic-specialties.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    GastronomicSpecialtiesComponent
  ],
  imports: [
    CommonModule,
    GastronomicSpecialtiesRoutingModule,
    FormsModule
  ]
})
export class GastronomicSpecialtiesModule { }
