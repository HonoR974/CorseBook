import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
   
    this.getProducts();
    console.log("product " + this.products);
    console.log("test => " + this.productService.getProductsList().subscribe(data => {
      this.products = data;
    }));

  }

  private getProducts()
  {
    this.productService.getProductsList().subscribe(data => {
      this.products = data;
    });
  }

}
