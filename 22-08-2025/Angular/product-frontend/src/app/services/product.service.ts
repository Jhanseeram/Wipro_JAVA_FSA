import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product';

@Injectable({ providedIn: 'root' })
export class ProductService {
  private http = inject(HttpClient);
  private baseUrl = 'http://localhost:8080/api/products';

  list(): Observable<Product[]> { return this.http.get<Product[]>(this.baseUrl); }
  get(id: number): Observable<Product> { return this.http.get<Product>(`${this.baseUrl}/${id}`); }
  create(p: Product): Observable<Product> { return this.http.post<Product>(this.baseUrl, p); }
  update(id: number, p: Product): Observable<Product> { return this.http.put<Product>(`${this.baseUrl}/${id}`, p); }
  delete(id: number) { return this.http.delete(`${this.baseUrl}/${id}`); }
}
