import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddOwnerComponent } from './add-owner/add-owner.component';

const routes: Routes = [
  {path:'',component:AddOwnerComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AddOwnerRoutingModule { }
