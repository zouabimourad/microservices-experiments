import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ProductComponent} from './product/product.component';
import {ProductResolver} from "./product.service";

import {OAuthModule} from 'angular-oauth2-oidc';
import {HomeComponent} from './home/home.component';
import {TokenInterceptor} from "./token.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    OAuthModule.forRoot(),
    AppRoutingModule
  ],
  providers: [
    ProductResolver,
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

}
