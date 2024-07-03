import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GuestHousesComponent } from './guest-houses/guest-houses.component';

const routes: Routes = [
  {path:'',component:GuestHousesComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GuestHousesRoutingModule { }
