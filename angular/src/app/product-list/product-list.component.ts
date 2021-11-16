import { Component, OnInit } from '@angular/core';
import { Product } from '../product';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];

  constructor() { }

  ngOnInit(): void {
    this.products = [{
      "id":1,
      "name":"jambon",
      "type":"charcuterie"
        },
      {
        "id":2,
      "name":"fromage",
      "type":"cremerie"
      }];
  }

}
