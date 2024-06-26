import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLayouteComponent } from './layoutes/admin-layoute/admin-layoute.component';
import { authenticationGuard } from './guards/authentication.guard';

const routes: Routes = [
  {path:'admin',component:AdminLayouteComponent,canActivate:[authenticationGuard],data: {roles: ['Admin']},children:[
    {path:'',redirectTo:'dashboard',pathMatch:'full'},
    {path:'dashboard',loadChildren:()=> import('./views/Admin/dashboard/dashboard.module').then(m=>m.DashboardModule)},
    {path:'profile',loadChildren:()=> import('./views/Admin/profile/profile.module').then(m=>m.ProfileModule)},
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
