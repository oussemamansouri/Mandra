import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UpdateClientComponent } from './update-client/update-client.component';

const routes: Routes = [
  {path:'',component:UpdateClientComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UpdateClientRoutingModule { }
