import {Component, OnInit} from '@angular/core';
import {ProductService} from "../product.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  constructor(productService: ProductService, private route: ActivatedRoute) {

  }

  products: any;

  ngOnInit() {

    this.route.data.subscribe(data => {
      this.products = data.products;
    });

  }

}
