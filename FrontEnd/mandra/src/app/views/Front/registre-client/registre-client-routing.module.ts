import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistreClientComponent } from './registre-client/registre-client.component';

const routes: Routes = [
  {path:'',component:RegistreClientComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegistreClientRoutingModule { }
