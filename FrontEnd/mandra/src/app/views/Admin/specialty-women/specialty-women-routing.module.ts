import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SpecialtyWomenComponent } from './specialty-women/specialty-women.component';

const routes: Routes = [
  {path:'',component:SpecialtyWomenComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SpecialtyWomenRoutingModule { }
