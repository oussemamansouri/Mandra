import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SpecialtyWomensComponent } from './specialty-womens/specialty-womens.component';

const routes: Routes = [
  {path:'',component:SpecialtyWomensComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SpecialtyWomensRoutingModule { }
