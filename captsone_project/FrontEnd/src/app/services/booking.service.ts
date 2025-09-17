import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  // This URL would point to your Booking Microservice
  private baseUrl = environment.bookingServiceUrl;

  constructor(private http: HttpClient) {}

  createBooking(booking: any): Observable<any> {
    console.log('=== BOOKING SERVICE DEBUG ===');
    console.log('Input booking data received:', booking);
    console.log('Base URL:', this.baseUrl);
    
    // Map Angular booking data to Booking-MS format
    const bookingRequest = {
      customerName: booking.passengerName || 'Unknown',
      customerEmail: booking.email || 'unknown@example.com',
      flightDetails: `${booking.fromLocation} to ${booking.toLocation} - Flight`,
      amount: booking.price || 0,
      paymentMethod: 'CREDIT_CARD'
    };
    
    console.log('Mapped booking request for backend:', bookingRequest);
    console.log('Making POST request to:', `${this.baseUrl}/create`);
    
    return this.http.post(`${this.baseUrl}/create`, bookingRequest).pipe(
      tap({
        next: (response: any) => {
          console.log('✅ Booking API SUCCESS response:', response);
        },
        error: (error: any) => {
          console.error('❌ Booking API ERROR:', error);
          console.error('Error status:', error.status);
          console.error('Error message:', error.message);
          console.error('Error details:', error.error);
        }
      })
    );
  }

  getAllBookings(): Observable<any> {
    return this.http.get(`${this.baseUrl}/readAllBooking`);
  }

  getBookingById(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}/status`);
  }

  updateBooking(booking: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/updateBooking`, booking);
  }

  deleteBooking(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/deleteBooking/${id}`);
  }
}
