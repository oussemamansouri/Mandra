import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OwnerDetailsComponent } from './owner-details/owner-details.component';

const routes: Routes = [
  {path:'',component:OwnerDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OwnerDetailsRoutingModule { }
