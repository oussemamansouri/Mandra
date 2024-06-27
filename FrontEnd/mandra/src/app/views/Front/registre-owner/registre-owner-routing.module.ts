import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistreOwnerComponent } from './registre-owner/registre-owner.component';

const routes: Routes = [
{path:'',component:RegistreOwnerComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegistreOwnerRoutingModule { }
