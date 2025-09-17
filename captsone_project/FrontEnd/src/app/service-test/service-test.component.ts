import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FlightService } from '../services/flight.service';
import { BookingService } from '../services/booking.service';
import { PaymentService } from '../services/payment.service';

@Component({
  selector: 'app-service-test',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="service-test-container">
      <h2>Microservice Connection Test</h2>
      
      <div class="test-section">
        <h3>Flight Service (Port 9017)</h3>
        <button (click)="testFlightService()" [disabled]="testing.flights">
          {{ testing.flights ? 'Testing...' : 'Test Flight Service' }}
        </button>
        <div class="result" [class.success]="results.flights?.success" [class.error]="results.flights?.success === false">
          {{ results.flights?.message }}
        </div>
        <div *ngIf="results.flights?.data" class="data">
          <p>Flights found: {{ results.flights.data.length }}</p>
          <div *ngFor="let flight of results.flights.data" class="flight-item">
            {{ flight.flightNumber }} - {{ flight.airline }} - {{ flight.departureCity }} to {{ flight.arrivalCity }} - ₹{{ flight.price }}
          </div>
        </div>
      </div>

      <div class="test-section">
        <h3>Booking Service (Port 9018)</h3>
        <button (click)="testBookingService()" [disabled]="testing.booking">
          {{ testing.booking ? 'Testing...' : 'Test Booking Service' }}
        </button>
        <div class="result" [class.success]="results.booking?.success" [class.error]="results.booking?.success === false">
          {{ results.booking?.message }}
        </div>
        <div *ngIf="results.booking?.data" class="data">
          <p>Booking ID: {{ results.booking.data.bookingId }}</p>
          <p>Status: {{ results.booking.data.status }}</p>
        </div>
      </div>

      <div class="test-section">
        <h3>Payment Service (Port 9019)</h3>
        <button (click)="testPaymentService()" [disabled]="testing.payment">
          {{ testing.payment ? 'Testing...' : 'Test Payment Service' }}
        </button>
        <div class="result" [class.success]="results.payment?.success" [class.error]="results.payment?.success === false">
          {{ results.payment?.message }}
        </div>
        <div *ngIf="results.payment?.data" class="data">
          <p>Payment ID: {{ results.payment.data.paymentId }}</p>
          <p>Status: {{ results.payment.data.status }}</p>
        </div>
      </div>

      <div class="test-section">
        <button (click)="testAllServices()" [disabled]="testingAll">
          {{ testingAll ? 'Testing All...' : 'Test All Services' }}
        </button>
      </div>
    </div>
  `,
  styles: [`
    .service-test-container {
      padding: 20px;
      max-width: 800px;
      margin: 0 auto;
    }
    
    .test-section {
      margin: 20px 0;
      padding: 15px;
      border: 1px solid #ddd;
      border-radius: 5px;
    }
    
    button {
      background-color: #007bff;
      color: white;
      border: none;
      padding: 10px 20px;
      border-radius: 5px;
      cursor: pointer;
      margin-bottom: 10px;
    }
    
    button:disabled {
      background-color: #6c757d;
      cursor: not-allowed;
    }
    
    .result {
      padding: 10px;
      margin: 10px 0;
      border-radius: 3px;
    }
    
    .result.success {
      background-color: #d4edda;
      color: #155724;
      border: 1px solid #c3e6cb;
    }
    
    .result.error {
      background-color: #f8d7da;
      color: #721c24;
      border: 1px solid #f5c6cb;
    }
    
    .data {
      background-color: #f8f9fa;
      padding: 10px;
      border-radius: 3px;
      margin-top: 10px;
    }
    
    .flight-item {
      padding: 5px;
      margin: 5px 0;
      background-color: white;
      border-radius: 3px;
      border: 1px solid #dee2e6;
    }
  `]
})
export class ServiceTestComponent {
  testing = {
    flights: false,
    booking: false,
    payment: false
  };
  
  testingAll = false;
  
  results: any = {};

  constructor(
    private flightService: FlightService,
    private bookingService: BookingService,
    private paymentService: PaymentService
  ) {}

  testFlightService() {
    this.testing.flights = true;
    this.results.flights = null;
    
    this.flightService.getAllFlights().subscribe({
      next: (data) => {
        this.testing.flights = false;
        this.results.flights = {
          success: true,
          message: 'Flight Service connected successfully!',
          data: data
        };
      },
      error: (error) => {
        this.testing.flights = false;
        this.results.flights = {
          success: false,
          message: 'Flight Service connection failed: ' + error.message
        };
      }
    });
  }

  testBookingService() {
    this.testing.booking = true;
    this.results.booking = null;
    
    const testBooking = {
      passengerName: 'Test User',
      fromLocation: 'Test Origin',
      toLocation: 'Test Destination',
      price: 5000,
      flightNumber: 'TEST-123'
    };
    
    this.bookingService.createBooking(testBooking).subscribe({
      next: (data) => {
        this.testing.booking = false;
        this.results.booking = {
          success: true,
          message: 'Booking Service connected successfully!',
          data: data
        };
      },
      error: (error) => {
        this.testing.booking = false;
        this.results.booking = {
          success: false,
          message: 'Booking Service connection failed: ' + error.message
        };
      }
    });
  }

  testPaymentService() {
    this.testing.payment = true;
    this.results.payment = null;
    
    const testPayment = {
      bookingId: 'TEST-BOOKING-123',
      customerName: 'Test User',
      customerEmail: 'test@example.com',
      amount: 5000,
      paymentMethod: 'CREDIT_CARD',
      cardNumber: '4111111111111111',
      expiryDate: '12/25',
      cvv: '123'
    };
    
    this.paymentService.processPayment(testPayment).subscribe({
      next: (data) => {
        this.testing.payment = false;
        this.results.payment = {
          success: true,
          message: 'Payment Service connected successfully!',
          data: data
        };
      },
      error: (error) => {
        this.testing.payment = false;
        this.results.payment = {
          success: false,
          message: 'Payment Service connection failed: ' + error.message
        };
      }
    });
  }

  testAllServices() {
    this.testingAll = true;
    this.results = {};
    
    Promise.all([
      new Promise(resolve => {
        this.testFlightService();
        setTimeout(() => resolve(true), 2000);
      }),
      new Promise(resolve => {
        this.testBookingService();
        setTimeout(() => resolve(true), 2000);
      }),
      new Promise(resolve => {
        this.testPaymentService();
        setTimeout(() => resolve(true), 2000);
      })
    ]).then(() => {
      this.testingAll = false;
    });
  }
}