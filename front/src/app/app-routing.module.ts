import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProductComponent} from "./product/product.component";
import {ProductResolver} from "./product.service";
import {HomeComponent} from "./home/home.component";
import {AuthenticationGuard} from "./authentication.guard";

const routes: Routes = [
  {
    path: 'products', component: ProductComponent,
    resolve: {
      products: ProductResolver
    },
    canActivate: [AuthenticationGuard]
  },
  {
    path: '', component: HomeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
