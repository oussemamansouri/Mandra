import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActiveClientsComponent } from './active-clients/active-clients.component';

const routes: Routes = [
  {path:'',component:ActiveClientsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActiveClientsRoutingModule { }
