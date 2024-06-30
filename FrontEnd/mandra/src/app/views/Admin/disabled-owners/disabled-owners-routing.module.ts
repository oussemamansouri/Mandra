import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DisabledOwnersComponent } from './disabled-owners/disabled-owners.component';

const routes: Routes = [
  {path:'',component:DisabledOwnersComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DisabledOwnersRoutingModule { }
