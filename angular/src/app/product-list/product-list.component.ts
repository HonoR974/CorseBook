import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../_class/product';
import { ProductService } from '../_services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];

  constructor(private productService: ProductService, private router: Router) { }

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


  
  productDetails(id:number)
  {
    this.router.navigate(['product-details', id]);
  }


  updateProduct(id:number)
  {
    this.router.navigate(['update-product', id]);
  }

  deleteProduct(id:number)
  {
    this.productService.deleteProduct(id).subscribe(data => {
     console.log(data);
      this.getProducts();
    })
  }

}
