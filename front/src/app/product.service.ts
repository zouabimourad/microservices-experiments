import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {Resolve} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {

  }

  getProducts(): Observable<any> {
    return this.http.get('/rest/product');
  }

}

@Injectable()
export class ProductResolver implements Resolve<any[]> {
  constructor(private productService: ProductService) {
  }

  resolve(): Observable<any> {
    return this.productService.getProducts();
  }
}
