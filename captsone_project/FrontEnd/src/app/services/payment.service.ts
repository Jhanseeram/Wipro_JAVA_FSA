import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface PaymentRequest {
  bookingId: string;
  customerName: string;
  customerEmail: string;
  amount: number;
  paymentMethod: string;
  cardNumber: string;
  expiryDate: string;
  cvv: string;
}

export interface PaymentResponse {
  paymentId: string;
  status: string;
  message: string;
  transactionId: string;
}

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private baseUrl = environment.paymentServiceUrl;

  constructor(private http: HttpClient) { }

  processPayment(paymentRequest: PaymentRequest): Observable<PaymentResponse> {
    return this.http.post<PaymentResponse>(`${this.baseUrl}/process`, paymentRequest);
  }

  getPaymentStatus(paymentId: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/status/${paymentId}`);
  }
}