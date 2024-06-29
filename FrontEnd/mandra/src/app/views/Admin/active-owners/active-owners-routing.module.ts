import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActiveOwnersComponent } from './active-owners/active-owners.component';

const routes: Routes = [
  {path:'',component:ActiveOwnersComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActiveOwnersRoutingModule { }
