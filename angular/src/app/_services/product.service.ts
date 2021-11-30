import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../_class/product';

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

  createProduct(product: Product): Observable<Object>
  {
    return this.HttpClient.post(`${this.baseURL}`, product);
  }

  getProductById(id:number):Observable<Product>
  {
    return this.HttpClient.get<Product>(`${this.baseURL}/${id}` );
  }

  updateProduct(id:number, product: Product):Observable<Object>
  {
    return this.HttpClient.put(`${this.baseURL}/${id}`, product );
  }

  deleteProduct(id: number) :Observable<Object> {
    return this.HttpClient.delete(`${this.baseURL}/${id}`);
  }
}
