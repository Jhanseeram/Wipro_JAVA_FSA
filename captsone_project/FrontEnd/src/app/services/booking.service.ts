import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  // This URL would point to your Booking Microservice
  private baseUrl = environment.bookingServiceUrl;

  constructor(private http: HttpClient) {}

  createBooking(booking: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/createBooking`, booking);
  }

  getAllBookings(): Observable<any> {
    return this.http.get(`${this.baseUrl}/readAllBooking`);
  }

  getBookingById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/searchBooking/${id}`);
  }

  updateBooking(booking: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/updateBooking`, booking);
  }

  deleteBooking(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/deleteBooking/${id}`);
  }
}
