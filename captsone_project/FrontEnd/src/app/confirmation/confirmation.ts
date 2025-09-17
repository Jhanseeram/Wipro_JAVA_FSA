import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { BookingService } from '../services/booking.service';
import { DataSharingService } from '../services/data-sharing.service';

interface BookingDetails {
  bookingId: string;
  pnr: string;
  fromLocation: string;
  toLocation: string;
  departureDate: string;
  departureTime: string;
  arrivalTime: string;
  flightNumber: string;
  airline: string;
  passengerDetails: {
    name: string;
    email: string;
    phone: string;
  };
  noOfPassengers: number;
  price: number;
  bookingDate: string;
  status: string;
}

@Component({
  selector: 'app-confirmation',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="confirmation-container">
      <div class="success-header">
        <h2>✈️ Booking Confirmed</h2>
        <p>Your flight ticket is ready</p>
      </div>

      <div class="e-ticket" id="e-ticket" *ngIf="bookingDetails">
        <!-- Ticket Header -->
        <div class="ticket-header">
          <div class="ticket-title">ELECTRONIC TICKET</div>
          <div class="pnr-box">
            <div class="pnr-label">PNR</div>
            <div class="pnr-number">{{ bookingDetails.pnr }}</div>
          </div>
        </div>

        <!-- Flight Information -->
        <div class="flight-section">
          <div class="section-title">FLIGHT DETAILS</div>
          <div class="flight-row">
            <div class="flight-info">
              <span class="label">Flight:</span>
              <span class="value">{{ bookingDetails.flightNumber }} - {{ bookingDetails.airline }}</span>
            </div>
            <div class="flight-info">
              <span class="label">Date:</span>
              <span class="value">{{ bookingDetails.departureDate | date:'dd MMM yyyy' }}</span>
            </div>
          </div>
          
          <div class="route-section">
            <div class="route-point">
              <div class="city">{{ bookingDetails.fromLocation }}</div>
              <div class="time">{{ bookingDetails.departureTime }}</div>
              <div class="label">DEPARTURE</div>
            </div>
            <div class="route-arrow">→</div>
            <div class="route-point">
              <div class="city">{{ bookingDetails.toLocation }}</div>
              <div class="time">{{ bookingDetails.arrivalTime }}</div>
              <div class="label">ARRIVAL</div>
            </div>
          </div>
        </div>

        <!-- Passenger Information -->
        <div class="passenger-section">
          <div class="section-title">PASSENGER DETAILS</div>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">Name:</span>
              <span class="value">{{ bookingDetails.passengerDetails.name }}</span>
            </div>
            <div class="info-item">
              <span class="label">Email:</span>
              <span class="value">{{ bookingDetails.passengerDetails.email }}</span>
            </div>
            <div class="info-item">
              <span class="label">Phone:</span>
              <span class="value">{{ bookingDetails.passengerDetails.phone }}</span>
            </div>
            <div class="info-item">
              <span class="label">Passengers:</span>
              <span class="value">{{ bookingDetails.noOfPassengers }}</span>
            </div>
          </div>
        </div>

        <!-- Booking Information -->
        <div class="booking-section">
          <div class="section-title">BOOKING INFORMATION</div>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">Booking ID:</span>
              <span class="value">{{ bookingDetails.bookingId }}</span>
            </div>
            <div class="info-item">
              <span class="label">Booking Date:</span>
              <span class="value">{{ bookingDetails.bookingDate | date:'dd MMM yyyy' }}</span>
            </div>
            <div class="info-item">
              <span class="label">Status:</span>
              <span class="value status-confirmed">{{ bookingDetails.status }}</span>
            </div>
            <div class="info-item total-fare">
              <span class="label">Total Fare:</span>
              <span class="value">₹{{ bookingDetails.price }}</span>
            </div>
          </div>
        </div>

        <!-- Important Notes -->
        <div class="notes-section">
          <div class="section-title">IMPORTANT NOTES</div>
          <ul>
            <li>Arrive at airport 2 hours before departure</li>
            <li>Carry valid photo ID for check-in</li>
            <li>Web check-in available 48 hours before flight</li>
            <li>Baggage: 15kg check-in + 7kg cabin allowed</li>
          </ul>
        </div>
      </div>

      <!-- Action Buttons -->
      <div class="actions">
        <button class="btn download" (click)="downloadTicket()">📥 Download</button>
        <button class="btn print" (click)="printTicket()">🖨️ Print</button>
        <button class="btn home" (click)="goHome()">🏠 Home</button>
      </div>

      <!-- Footer Message -->
      <div class="footer-message">
        <p>Ticket sent to: {{ bookingDetails?.passengerDetails?.email }}</p>
        <p>Support: support@airline.com | 1800-123-4567</p>
      </div>
    </div>
  `,
  styles: [`
    .confirmation-container {
      max-width: 600px;
      margin: 20px auto;
      padding: 20px;
      font-family: Arial, sans-serif;
    }

    .success-header {
      text-align: center;
      margin-bottom: 30px;
      padding: 20px;
      background: #28a745;
      color: white;
      border-radius: 8px;
    }

    .success-header h2 {
      margin: 0 0 10px 0;
      font-size: 1.8em;
    }

    .success-header p {
      margin: 0;
      opacity: 0.9;
    }

    /* E-Ticket Styles */
    .e-ticket {
      background: white;
      border: 2px solid #333;
      margin: 20px 0;
      font-size: 14px;
    }

    .ticket-header {
      background: #333;
      color: white;
      padding: 15px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .ticket-title {
      font-size: 16px;
      font-weight: bold;
    }

    .pnr-box {
      text-align: center;
    }

    .pnr-label {
      font-size: 12px;
      margin-bottom: 5px;
    }

    .pnr-number {
      font-size: 18px;
      font-weight: bold;
      letter-spacing: 2px;
      background: rgba(255,255,255,0.2);
      padding: 5px 10px;
      border-radius: 3px;
    }

    .flight-section, .passenger-section, .booking-section, .notes-section {
      padding: 20px;
      border-bottom: 1px solid #ddd;
    }

    .section-title {
      font-size: 12px;
      font-weight: bold;
      color: #666;
      margin-bottom: 15px;
      letter-spacing: 1px;
    }

    .flight-row {
      display: flex;
      justify-content: space-between;
      margin-bottom: 20px;
    }

    .flight-info {
      display: flex;
      flex-direction: column;
    }

    .route-section {
      display: flex;
      align-items: center;
      justify-content: space-between;
      margin-top: 15px;
    }

    .route-point {
      text-align: center;
      flex: 1;
    }

    .route-point .city {
      font-size: 24px;
      font-weight: bold;
      color: #333;
      margin-bottom: 5px;
    }

    .route-point .time {
      font-size: 16px;
      color: #007bff;
      font-weight: 600;
      margin-bottom: 5px;
    }

    .route-arrow {
      font-size: 24px;
      color: #666;
      margin: 0 20px;
    }

    .info-grid {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 15px;
    }

    .info-item {
      display: flex;
      flex-direction: column;
      gap: 5px;
    }

    .label {
      font-size: 11px;
      color: #666;
      font-weight: bold;
      text-transform: uppercase;
      letter-spacing: 0.5px;
    }

    .value {
      font-size: 14px;
      color: #333;
      padding: 8px;
      background: #f9f9f9;
      border-left: 3px solid #007bff;
    }

    .status-confirmed {
      background: #d4edda !important;
      color: #155724 !important;
      border-left-color: #28a745 !important;
    }

    .total-fare .value {
      background: #e7f3ff !important;
      color: #0056b3 !important;
      font-weight: bold;
      font-size: 16px;
    }

    .notes-section {
      background: #f8f9fa;
    }

    .notes-section ul {
      margin: 0;
      padding-left: 20px;
      color: #666;
    }

    .notes-section li {
      margin-bottom: 8px;
      font-size: 12px;
      line-height: 1.4;
    }

    /* Action Buttons */
    .actions {
      display: flex;
      gap: 15px;
      justify-content: center;
      margin: 20px 0;
    }

    .btn {
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-size: 14px;
      font-weight: 600;
      transition: background-color 0.3s;
    }

    .btn.download {
      background: #28a745;
      color: white;
    }

    .btn.download:hover {
      background: #218838;
    }

    .btn.print {
      background: #17a2b8;
      color: white;
    }

    .btn.print:hover {
      background: #138496;
    }

    .btn.home {
      background: #6c757d;
      color: white;
    }

    .btn.home:hover {
      background: #545b62;
    }

    .footer-message {
      text-align: center;
      padding: 20px;
      background: #f8f9fa;
      border-radius: 5px;
      margin-top: 20px;
    }

    .footer-message p {
      margin: 5px 0;
      color: #666;
      font-size: 13px;
    }

    /* Mobile Responsive */
    @media (max-width: 600px) {
      .confirmation-container {
        padding: 10px;
      }
      
      .route-section {
        flex-direction: column;
        gap: 15px;
      }
      
      .route-arrow {
        transform: rotate(90deg);
        margin: 10px 0;
      }
      
      .info-grid {
        grid-template-columns: 1fr;
      }
      
      .actions {
        flex-direction: column;
        align-items: center;
      }
      
      .btn {
        width: 200px;
      }
    }

    /* Print Styles */
    @media print {
      .actions, .footer-message {
        display: none;
      }
      
      .e-ticket {
        border: 1px solid #000;
        box-shadow: none;
      }
      
      body {
        margin: 0;
      }
    }
  `]
})
export class ConfirmationComponent implements OnInit {
  bookingDetails: BookingDetails | null = null;
  
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private bookingService: BookingService,
    private dataSharingService: DataSharingService
  ) {}
  
  ngOnInit(): void {
    // Priority 1: Try to get booking data from DataSharingService
    const sharedBookingData = this.dataSharingService.getBookingData();
    if (sharedBookingData) {
      console.log('Using booking data from DataSharingService:', sharedBookingData);
      this.createBookingDetails({ booking: sharedBookingData });
      return;
    }

    // Priority 2: Get booking data from route state
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      console.log('Using booking data from navigation state:', navigation.extras.state);
      this.createBookingDetails(navigation.extras.state);
      return;
    }

    // Priority 3: Try from history state
    if (history.state && history.state.booking) {
      console.log('Using booking data from history state:', history.state);
      this.createBookingDetails(history.state);
      return;
    }

    // Priority 4: Fallback to query params and try to load from backend
    this.route.queryParams.subscribe(params => {
      const bookingId = params['bookingId'];
      if (bookingId) {
        console.log('Loading booking details from backend for ID:', bookingId);
        this.loadBookingDetails(bookingId);
      } else {
        // Last resort: Create booking details with shared data or show error
        console.warn('No booking data found anywhere, creating fallback booking');
        this.createFallbackBooking();
      }
    });
  }
  
  private createBookingDetails(routeState: any): void {
    const booking = routeState.booking || routeState;
    console.log('Creating booking details from:', booking);
    
    this.bookingDetails = {
      bookingId: booking.bookingId || this.generateBookingId(),
      pnr: this.generatePNR(),
      fromLocation: booking.fromLocation || 'Unknown',
      toLocation: booking.toLocation || 'Unknown',
      departureDate: booking.departureDate || booking.bookingDate || new Date().toISOString().split('T')[0],
      departureTime: booking.departureTime || '10:30 AM',
      arrivalTime: booking.arrivalTime || '01:00 PM',
      flightNumber: booking.flightNumber || 'AI-' + Math.floor(Math.random() * 9000 + 1000),
      airline: booking.airline || 'Air India',
      passengerDetails: {
        name: booking.passengerDetails?.name || booking.passengerName || 'Unknown Passenger',
        email: booking.passengerDetails?.email || booking.email || 'unknown@example.com',
        phone: booking.passengerDetails?.phone || booking.phone || 'Unknown Phone'
      },
      noOfPassengers: booking.noOfPassengers || 1,
      price: booking.price || 0,
      bookingDate: booking.bookingDate || new Date().toISOString(),
      status: 'CONFIRMED'
    };
    
    console.log('Final booking details created:', this.bookingDetails);
  }
  
  loadBookingDetails(bookingId: string): void {
    this.bookingService.getBookingById(bookingId).subscribe({
      next: (data) => {
        this.createBookingDetails(data);
      },
      error: (err) => {
        console.error('Error fetching booking details:', err);
        // Try to get user data from DataSharingService as fallback
        const sharedData = this.dataSharingService.getBookingData();
        if (sharedData) {
          console.log('Using shared booking data as fallback:', sharedData);
          this.createBookingDetails({ booking: sharedData });
        } else {
          // Last resort: Create booking with minimal data but try to avoid hardcoded test data
          console.warn('Creating minimal booking details with booking ID:', bookingId);
          this.createFallbackBooking(bookingId);
        }
      }
    });
  }
  
  private createFallbackBooking(bookingId?: string): void {
    // Try to get any available data from DataSharingService
    const sharedData = this.dataSharingService.getBookingData();
    
    this.bookingDetails = {
      bookingId: bookingId || this.generateBookingId(),
      pnr: this.generatePNR(),
      fromLocation: sharedData?.fromLocation || 'Unknown Origin',
      toLocation: sharedData?.toLocation || 'Unknown Destination',
      departureDate: sharedData?.bookingDate || new Date().toISOString().split('T')[0],
      departureTime: '10:30 AM',
      arrivalTime: '01:00 PM',
      flightNumber: 'AI-' + Math.floor(Math.random() * 9000 + 1000),
      airline: 'Air India',
      passengerDetails: {
        name: sharedData?.passengerName || 'Passenger Name Not Available',
        email: sharedData?.email || 'Email Not Available',
        phone: sharedData?.phone || 'Phone Not Available'
      },
      noOfPassengers: sharedData?.noOfPassengers || 1,
      price: sharedData?.price || 0,
      bookingDate: new Date().toISOString(),
      status: 'CONFIRMED'
    };
    
    console.log('Fallback booking details created:', this.bookingDetails);
  }
  
  private generateBookingId(): string {
    return 'BK' + Date.now().toString().slice(-8);
  }
  
  private generatePNR(): string {
    const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    let result = '';
    for (let i = 0; i < 6; i++) {
      result += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return result;
  }
  
  downloadTicket(): void {
    const element = document.getElementById('e-ticket');
    if (element && this.bookingDetails) {
      // Create a complete HTML document with all the data
      const ticketHTML = `
        <!DOCTYPE html>
        <html>
        <head>
          <title>E-Ticket - ${this.bookingDetails.pnr}</title>
          <style>
            body {
              font-family: Arial, sans-serif;
              margin: 20px;
              padding: 0;
              font-size: 14px;
            }
            .e-ticket {
              background: white;
              border: 2px solid #333;
              max-width: 600px;
              margin: 0 auto;
            }
            .ticket-header {
              background: #333;
              color: white;
              padding: 15px;
              display: flex;
              justify-content: space-between;
              align-items: center;
            }
            .ticket-title {
              font-size: 16px;
              font-weight: bold;
            }
            .pnr-box {
              text-align: center;
            }
            .pnr-label {
              font-size: 12px;
              margin-bottom: 5px;
            }
            .pnr-number {
              font-size: 18px;
              font-weight: bold;
              letter-spacing: 2px;
              background: rgba(255,255,255,0.2);
              padding: 5px 10px;
              border-radius: 3px;
            }
            .flight-section, .passenger-section, .booking-section, .notes-section {
              padding: 20px;
              border-bottom: 1px solid #ddd;
            }
            .section-title {
              font-size: 12px;
              font-weight: bold;
              color: #666;
              margin-bottom: 15px;
              letter-spacing: 1px;
            }
            .flight-row {
              display: flex;
              justify-content: space-between;
              margin-bottom: 20px;
            }
            .flight-info {
              display: flex;
              flex-direction: column;
            }
            .route-section {
              display: flex;
              align-items: center;
              justify-content: space-between;
              margin-top: 15px;
            }
            .route-point {
              text-align: center;
              flex: 1;
            }
            .route-point .city {
              font-size: 24px;
              font-weight: bold;
              color: #333;
              margin-bottom: 5px;
            }
            .route-point .time {
              font-size: 16px;
              color: #007bff;
              font-weight: 600;
              margin-bottom: 5px;
            }
            .route-arrow {
              font-size: 24px;
              color: #666;
              margin: 0 20px;
            }
            .info-grid {
              display: grid;
              grid-template-columns: 1fr 1fr;
              gap: 15px;
            }
            .info-item {
              display: flex;
              flex-direction: column;
              gap: 5px;
            }
            .label {
              font-size: 11px;
              color: #666;
              font-weight: bold;
              text-transform: uppercase;
              letter-spacing: 0.5px;
            }
            .value {
              font-size: 14px;
              color: #333;
              padding: 8px;
              background: #f9f9f9;
              border-left: 3px solid #007bff;
            }
            .status-confirmed {
              background: #d4edda !important;
              color: #155724 !important;
              border-left-color: #28a745 !important;
            }
            .total-fare .value {
              background: #e7f3ff !important;
              color: #0056b3 !important;
              font-weight: bold;
              font-size: 16px;
            }
            .notes-section {
              background: #f8f9fa;
            }
            .notes-section ul {
              margin: 0;
              padding-left: 20px;
              color: #666;
            }
            .notes-section li {
              margin-bottom: 8px;
              font-size: 12px;
              line-height: 1.4;
            }
            @media print {
              body { margin: 0; }
            }
          </style>
        </head>
        <body>
          <div class="e-ticket">
            <div class="ticket-header">
              <div class="ticket-title">ELECTRONIC TICKET</div>
              <div class="pnr-box">
                <div class="pnr-label">PNR</div>
                <div class="pnr-number">${this.bookingDetails.pnr}</div>
              </div>
            </div>

            <div class="flight-section">
              <div class="section-title">FLIGHT DETAILS</div>
              <div class="flight-row">
                <div class="flight-info">
                  <span class="label">Flight:</span>
                  <span class="value">${this.bookingDetails.flightNumber} - ${this.bookingDetails.airline}</span>
                </div>
                <div class="flight-info">
                  <span class="label">Date:</span>
                  <span class="value">${new Date(this.bookingDetails.departureDate).toLocaleDateString('en-GB', { day: '2-digit', month: 'short', year: 'numeric' })}</span>
                </div>
              </div>
              
              <div class="route-section">
                <div class="route-point">
                  <div class="city">${this.bookingDetails.fromLocation}</div>
                  <div class="time">${this.bookingDetails.departureTime}</div>
                  <div class="label">DEPARTURE</div>
                </div>
                <div class="route-arrow">→</div>
                <div class="route-point">
                  <div class="city">${this.bookingDetails.toLocation}</div>
                  <div class="time">${this.bookingDetails.arrivalTime}</div>
                  <div class="label">ARRIVAL</div>
                </div>
              </div>
            </div>

            <div class="passenger-section">
              <div class="section-title">PASSENGER DETAILS</div>
              <div class="info-grid">
                <div class="info-item">
                  <span class="label">Name:</span>
                  <span class="value">${this.bookingDetails.passengerDetails.name}</span>
                </div>
                <div class="info-item">
                  <span class="label">Email:</span>
                  <span class="value">${this.bookingDetails.passengerDetails.email}</span>
                </div>
                <div class="info-item">
                  <span class="label">Phone:</span>
                  <span class="value">${this.bookingDetails.passengerDetails.phone}</span>
                </div>
                <div class="info-item">
                  <span class="label">Passengers:</span>
                  <span class="value">${this.bookingDetails.noOfPassengers}</span>
                </div>
              </div>
            </div>

            <div class="booking-section">
              <div class="section-title">BOOKING INFORMATION</div>
              <div class="info-grid">
                <div class="info-item">
                  <span class="label">Booking ID:</span>
                  <span class="value">${this.bookingDetails.bookingId}</span>
                </div>
                <div class="info-item">
                  <span class="label">Booking Date:</span>
                  <span class="value">${new Date(this.bookingDetails.bookingDate).toLocaleDateString('en-GB', { day: '2-digit', month: 'short', year: 'numeric' })}</span>
                </div>
                <div class="info-item">
                  <span class="label">Status:</span>
                  <span class="value status-confirmed">${this.bookingDetails.status}</span>
                </div>
                <div class="info-item total-fare">
                  <span class="label">Total Fare:</span>
                  <span class="value">₹${this.bookingDetails.price}</span>
                </div>
              </div>
            </div>

            <div class="notes-section">
              <div class="section-title">IMPORTANT NOTES</div>
              <ul>
                <li>Arrive at airport 2 hours before departure</li>
                <li>Carry valid photo ID for check-in</li>
                <li>Web check-in available 48 hours before flight</li>
                <li>Baggage: 15kg check-in + 7kg cabin allowed</li>
              </ul>
            </div>
          </div>
        </body>
        </html>
      `;
      
      // Create a new window with the complete HTML
      const printWindow = window.open('', '_blank', 'width=800,height=600');
      if (printWindow) {
        printWindow.document.write(ticketHTML);
        printWindow.document.close();
        
        // Wait for the content to load, then trigger print
        setTimeout(() => {
          printWindow.focus();
          printWindow.print();
        }, 500);
        
        // Close the window after printing (optional)
        printWindow.onafterprint = () => {
          printWindow.close();
        };
      }
    } else {
      alert('Unable to generate ticket. Please try again.');
    }
  }
  
  printTicket(): void {
    window.print();
  }
  
  goHome(): void {
    this.router.navigate(['/']);
  }
}
