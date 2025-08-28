import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '../models/order';

@Injectable({ providedIn: 'root' })
export class OrderService {
  private http = inject(HttpClient);
  private baseUrl = 'http://localhost:8080/api/orders';

  place(productId: number, qty: number): Observable<Order> {
    return this.http.post<Order>(`${this.baseUrl}/place`, { productId, qty });
  }

  history(): Observable<Order[]> {
    return this.http.get<Order[]>(this.baseUrl);
  }
}
