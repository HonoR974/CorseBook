import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../_services/product.service';
import { Product } from '../_class/product';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  id:number;
  product: Product = new Product();
  

  constructor(private productService: ProductService,
    private route:ActivatedRoute,
    private router:Router) { }

  
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.productService.getProductById(this.id).subscribe(data => {
      this.product = data;
    }, 
    error => console.log(error));
  }

  onSubmit()
  {
    this.productService.updateProduct(this.id, this.product).subscribe( data => {
      console.log("updated");
      this.goToProductList();
    }, 
    error => console.log(error));

  }

  goToProductList()
  {
    this.router.navigate(['/products'])
  }


}
