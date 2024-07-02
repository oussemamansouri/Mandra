import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GastronomicSpecialtiesComponent } from './gastronomic-specialties/gastronomic-specialties.component';

const routes: Routes = [
  {path:'',component:GastronomicSpecialtiesComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GastronomicSpecialtiesRoutingModule { }
