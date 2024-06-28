import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutesModule } from './layoutes/layoutes.module';
import { AuthServiceService } from './services/authService/auth-service.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BaseURL } from './Shared/base-url';
import { HttpInterceptor } from './Shared/http-interceptor';


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LayoutesModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpInterceptor, multi: true },
    { provide: 'BaseURL', useValue: BaseURL}
 ],  bootstrap: [AppComponent]
})
export class AppModule { }
