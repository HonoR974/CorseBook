import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../_class/product';


const baseURL = "http://localhost:8080/api/v1/products/";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ProductService {



  constructor(private HttpClient: HttpClient) { }


  getProductsList(): Observable<Product[]>
  {
   
    return this.HttpClient.get<Product[]>(baseURL , httpOptions);
  }

  createProduct(product: Product): Observable<Object>
  {
    return this.HttpClient.post(baseURL, product);
  }

  getProductById(id:number):Observable<Product>
  {
    return this.HttpClient.get<Product>(baseURL + id );
  }

  updateProduct(id:number, product: Product):Observable<Object>
  {
    return this.HttpClient.put(baseURL + id, product );
  }

  deleteProduct(id: number) :Observable<Object> {
    return this.HttpClient.delete(baseURL + id);
  }
}
