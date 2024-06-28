import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLayouteComponent } from './layoutes/admin-layoute/admin-layoute.component';
import { authenticationGuard } from './guards/authentication.guard';
import { FrontLayouteComponent } from './layoutes/front-layoute/front-layoute.component';

const routes: Routes = [
    {path:'admin',component:AdminLayouteComponent,canActivate:[authenticationGuard],data: {roles: ['ROLE_Admin']},children:[
    {path:'',redirectTo:'dashboard',pathMatch:'full'},
    {path:'dashboard',loadChildren:()=> import('./views/Admin/dashboard/dashboard.module').then(m=>m.DashboardModule)},
    {path:'profile',loadChildren:()=> import('./views/Admin/profile/profile.module').then(m=>m.ProfileModule)},
  ]},
  {path:'',component:FrontLayouteComponent,children:[
    {path:'',redirectTo:'home',pathMatch:'full'},
    {path:'home',loadChildren:()=>import('./views/Front/home/home.module').then(m=>m.HomeModule)},
    {path:'signin',loadChildren:()=>import('./views/Front/login/login.module').then(m=>m.LoginModule)},
    {path:'signup',loadChildren:()=>import('./views/Front/registre/registre.module').then(m=>m.RegistreModule)},
    {path:'signup/client',loadChildren:()=>import('./views/Front/registre-client/registre-client.module').then(m=>m.RegistreClientModule)},
    {path:'signup/owner',loadChildren:()=>import('./views/Front/registre-owner/registre-owner.module').then(m=>m.RegistreOwnerModule)},
  ]}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      scrollPositionRestoration: 'enabled', // Enable scroll restoration
      anchorScrolling: 'enabled', // Enable anchor scrolling
      scrollOffset: [0, 0], // Optional: Adjust the scroll offset if needed
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
