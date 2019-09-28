import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProductComponent} from "./product/product.component";
import {ProductResolver} from "./product.service";

const routes: Routes = [
  {
    path: 'products', component: ProductComponent,
    resolve: {
      products: ProductResolver
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
