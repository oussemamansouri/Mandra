import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UpdateOwnerComponent } from './update-owner/update-owner.component';

const routes: Routes = [
  {
    path:'',component:UpdateOwnerComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UpdateOwnerRoutingModule { }
