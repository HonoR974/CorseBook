import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from './product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {


  private baseURL = "http://localhost:8080/api/v1/products";
  constructor(private HttpClient: HttpClient) { }


  getProductsList(): Observable<Product[]>
  {
   
    return this.HttpClient.get<Product[]>(`${this.baseURL}`);
  }
}
