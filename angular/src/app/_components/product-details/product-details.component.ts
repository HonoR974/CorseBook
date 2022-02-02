import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/_class/product';
import { ProductService } from 'src/app/_services/product.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  id:number;
  product:Product;

  constructor(private route: ActivatedRoute, 
    private productService: ProductService) { }

  ngOnInit(): void {

    this.id = this.route.snapshot.params['id'];

    this.product = new Product();
    this.productService.getProductById(this.id).subscribe(data => {
      this.product = data;
    } );
  }

}
