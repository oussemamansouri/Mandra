import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DisabledClientsComponent } from './disabled-clients/disabled-clients.component';

const routes: Routes = [
  {path:'',component:DisabledClientsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DisabledClientsRoutingModule { }
