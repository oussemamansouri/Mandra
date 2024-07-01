import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLayouteComponent } from './layoutes/admin-layoute/admin-layoute.component';
import { authenticationGuard } from './guards/authentication.guard';
import { FrontLayouteComponent } from './layoutes/front-layoute/front-layoute.component';
import { ClientLayouteComponent } from './layoutes/client-layoute/client-layoute.component';
import { OwnerLayouteComponent } from './layoutes/owner-layoute/owner-layoute.component';

const routes: Routes = [
    {path:'admin',component:AdminLayouteComponent,canActivate:[authenticationGuard],data: {roles: ['ROLE_Admin']},children:[
    {path:'',redirectTo:'dashboard',pathMatch:'full'},
    {path:'dashboard',loadChildren:()=> import('./views/Admin/dashboard/dashboard.module').then(m=>m.DashboardModule)},
    {path:'profile',loadChildren:()=> import('./views/Admin/profile/profile.module').then(m=>m.ProfileModule)},
    {path:'owners/add',loadChildren:()=> import('./views/Admin/add-owner/add-owner.module').then(m=>m.AddOwnerModule)},
    {path:'owners/active',loadChildren:()=> import('./views/Admin/active-owners/active-owners.module').then(m=>m.ActiveOwnersModule)},
    {path:'owners/disabled',loadChildren:()=> import('./views/Admin/disabled-owners/disabled-owners.module').then(m=>m.DisabledOwnersModule)},
    {path:'owners/details',loadChildren:()=> import('./views/Admin/owner-details/owner-details.module').then(m=>m.OwnerDetailsModule)},
    {path:'owners/update',loadChildren:()=> import('./views/Admin/update-owner/update-owner.module').then(m=>m.UpdateOwnerModule)},
    {path:'clients/add',loadChildren:()=> import('./views/Admin/add-client/add-client.module').then(m=>m.AddClientModule)},
    {path:'clients/active',loadChildren:()=> import('./views/Admin/active-clients/active-clients.module').then(m=>m.ActiveClientsModule)},
    {path:'clients/disabled',loadChildren:()=> import('./views/Admin/disabled-clients/disabled-clients.module').then(m=>m.DisabledClientsModule)},
    {path:'clients/details',loadChildren:()=> import('./views/Admin/client-details/client-details.module').then(m=>m.ClientDetailsModule)},
    {path:'clients/update',loadChildren:()=> import('./views/Admin/update-client/update-client.module').then(m=>m.UpdateClientModule)},
    {path:'hotels',loadChildren:()=> import('./views/Admin/hotel/hotel.module').then(m=>m.HotelModule)},

  ]},

  {path:'client',component:ClientLayouteComponent,canActivate:[authenticationGuard],data: {roles: ['ROLE_Client']},children:[
    {path:'',redirectTo:'profile',pathMatch:'full'},
    {path:'profile',loadChildren:()=> import('./views/Client/profile/profile.module').then(m=>m.ProfileModule)},
  ]},

  {path:'owner',component:OwnerLayouteComponent,canActivate:[authenticationGuard],data: {roles: ['ROLE_Owner']},children:[
    {path:'',redirectTo:'profile',pathMatch:'full'},
    {path:'profile',loadChildren:()=> import('./views/Owner/profile/profile.module').then(m=>m.ProfileModule)},
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
