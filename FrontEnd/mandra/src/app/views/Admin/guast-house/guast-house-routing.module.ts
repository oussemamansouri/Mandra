import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GuastHouseComponent } from './guast-house/guast-house.component';

const routes: Routes = [
  {path:'',component:GuastHouseComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GuastHouseRoutingModule { }
