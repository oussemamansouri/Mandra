import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistreComponent } from './registre/registre.component';

const routes: Routes = [
  {path:'',component:RegistreComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegistreRoutingModule { }
