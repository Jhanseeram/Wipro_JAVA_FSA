import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from '../services/booking.service';
import { PaymentService, PaymentRequest } from '../services/payment.service';
import { DataSharingService } from '../services/data-sharing.service';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './payment.html',
  styleUrl: './payment.css'
})
export class PaymentComponent implements OnInit {
  bookingData: any;
  fallbackBookingId: string = 'BK' + Date.now(); // Store generated booking ID to prevent constant regeneration
  paymentMethods = [
    { id: 'card', name: 'Credit/Debit Card', icon: '💳' },
    { id: 'upi', name: 'UPI', icon: '📱' },
    { id: 'wallet', name: 'Digital Wallet', icon: '💰' },
    { id: 'netbanking', name: 'Net Banking', icon: '🏦' }
  ];
  selectedPaymentMethod = 'card';
  isProcessing = false;
  isSubmitted = false;
  
  // Form groups for different payment methods
  cardForm!: FormGroup;
  upiForm!: FormGroup;
  netBankingForm!: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private bookingService: BookingService,
    private paymentService: PaymentService,
    private formBuilder: FormBuilder,
    private dataSharingService: DataSharingService
  ) {
    this.initializeForms();
  }
  
  private initializeForms(): void {
    // Card payment form
    this.cardForm = this.formBuilder.group({
      cardNumber: ['', [
        Validators.required
      ]],
      expiryDate: ['', [
        Validators.required,
        Validators.pattern(/^(0[1-9]|1[0-2])\/([0-9]{2})$/),
        this.expiryDateValidator
      ]],
      cvv: ['', [
        Validators.required,
        Validators.pattern(/^[0-9]{3,4}$/)
      ]],
      cardholderName: ['', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50),
        Validators.pattern(/^[a-zA-Z\s]+$/)
      ]]
    });
    
    // UPI payment form
    this.upiForm = this.formBuilder.group({
      upiId: ['', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9\.\-_]{2,256}@[a-zA-Z][a-zA-Z]{2,64}$/)
      ]]
    });
    
    // Net Banking form
    this.netBankingForm = this.formBuilder.group({
      bankName: ['', [
        Validators.required
      ]]
    });
  }
  
  // Custom validators
  private expiryDateValidator(control: AbstractControl): { [key: string]: any } | null {
    const expiryDate = control.value;
    if (!expiryDate) return null;
    
    const [month, year] = expiryDate.split('/');
    const currentYear = new Date().getFullYear() % 100;
    const currentMonth = new Date().getMonth() + 1;
    
    const expMonth = parseInt(month);
    const expYear = parseInt(year);
    
    if (expYear < currentYear || (expYear === currentYear && expMonth < currentMonth)) {
      return { 'expiredCard': { value: control.value } };
    }
    
    return null;
  }
  
  // Getters for form controls
  get cardNumber() { return this.cardForm.get('cardNumber')!; }
  get expiryDate() { return this.cardForm.get('expiryDate')!; }
  get cvv() { return this.cardForm.get('cvv')!; }
  get cardholderName() { return this.cardForm.get('cardholderName')!; }
  get upiId() { return this.upiForm.get('upiId')!; }
  get bankName() { return this.netBankingForm.get('bankName')!; }
  
  // Get current form based on selected payment method
  getCurrentForm(): FormGroup {
    switch (this.selectedPaymentMethod) {
      case 'card': return this.cardForm;
      case 'upi': return this.upiForm;
      case 'netbanking': return this.netBankingForm;
      default: return new FormGroup({});
    }
  }
  
  // Error message methods
  getCardNumberErrors(): string {
    const control = this.cardNumber;
    if (control.errors && (control.dirty || control.touched || this.isSubmitted)) {
      if (control.errors['required']) return 'Card number is required';
    }
    return '';
  }
  
  getExpiryDateErrors(): string {
    const control = this.expiryDate;
    if (control.errors && (control.dirty || control.touched || this.isSubmitted)) {
      if (control.errors['required']) return 'Expiry date is required';
      if (control.errors['pattern']) return 'Format: MM/YY';
      if (control.errors['expiredCard']) return 'Card has expired';
    }
    return '';
  }
  
  getCvvErrors(): string {
    const control = this.cvv;
    if (control.errors && (control.dirty || control.touched || this.isSubmitted)) {
      if (control.errors['required']) return 'CVV is required';
      if (control.errors['pattern']) return 'CVV must be 3-4 digits';
    }
    return '';
  }
  
  getCardholderNameErrors(): string {
    const control = this.cardholderName;
    if (control.errors && (control.dirty || control.touched || this.isSubmitted)) {
      if (control.errors['required']) return 'Cardholder name is required';
      if (control.errors['minlength']) return 'Name must be at least 2 characters';
      if (control.errors['maxlength']) return 'Name cannot exceed 50 characters';
      if (control.errors['pattern']) return 'Name should only contain letters and spaces';
    }
    return '';
  }
  
  getUpiIdErrors(): string {
    const control = this.upiId;
    if (control.errors && (control.dirty || control.touched || this.isSubmitted)) {
      if (control.errors['required']) return 'UPI ID is required';
      if (control.errors['pattern']) return 'Please enter a valid UPI ID (e.g., user@paytm)';
    }
    return '';
  }
  
  getBankNameErrors(): string {
    const control = this.bankName;
    if (control.errors && (control.dirty || control.touched || this.isSubmitted)) {
      if (control.errors['required']) return 'Please select a bank';
    }
    return '';
  }

  ngOnInit(): void {
    console.log('Payment component initialized');
    
    // Method 1: Try to get booking data from the data sharing service
    this.bookingData = this.dataSharingService.getBookingData();
    if (this.bookingData) {
      console.log('Booking data received from DataSharingService:', this.bookingData);
    } else {
      // Method 2: Try to get booking data from route state (current navigation)
      const navigation = this.router.getCurrentNavigation();
      if (navigation?.extras?.state && navigation.extras.state['booking']) {
        this.bookingData = navigation.extras.state['booking'];
        console.log('Booking data received from current navigation:', this.bookingData);
        // Store it in the service for future use
        this.dataSharingService.setBookingData(this.bookingData);
      } else {
        // Method 3: Try to get from history.state (for page refreshes)
        const state = history.state;
        if (state && state.booking) {
          this.bookingData = state.booking;
          console.log('Booking data received from history state:', this.bookingData);
          // Store it in the service for future use
          this.dataSharingService.setBookingData(this.bookingData);
        } else {
          // Method 4: Try to get from route data if passed via queryParams
          this.route.queryParams.subscribe(params => {
            if (params['bookingData']) {
              try {
                this.bookingData = JSON.parse(params['bookingData']);
                console.log('Booking data received from query params:', this.bookingData);
                // Store it in the service for future use
                this.dataSharingService.setBookingData(this.bookingData);
              } catch (e) {
                console.error('Error parsing booking data from query params:', e);
                this.bookingData = null;
              }
            }
          });
        }
      }
    }
    
    // If we have booking data, pre-populate cardholder name
    if (this.bookingData) {
      if (this.bookingData.passengerName) {
        this.cardForm.patchValue({
          cardholderName: this.bookingData.passengerName
        });
      }
    } else {
      // No booking data found, show error
      console.error('No booking data found. Please go back and enter your details.');
    }
  }

  selectPaymentMethod(method: string): void {
    this.selectedPaymentMethod = method;
  }

  processPayment(): void {
    this.isSubmitted = true;
    const currentForm = this.getCurrentForm();
    
    // Validate current form
    if (currentForm.invalid) {
      Object.keys(currentForm.controls).forEach(key => {
        currentForm.get(key)?.markAsTouched();
      });
      console.log('Form validation failed');
      return;
    }
    
    this.isProcessing = true;
    console.log('Processing payment with method:', this.selectedPaymentMethod);
    console.log('Form data:', currentForm.value);
    
    // Option 1: Direct payment through Payment-MS (new approach)
    if (this.selectedPaymentMethod === 'card') {
      const paymentRequest: PaymentRequest = {
        bookingId: 'TEMP_' + Date.now(), // Temporary booking ID
        customerName: this.bookingData.passengerName || 'Unknown',
        customerEmail: this.bookingData.email || 'unknown@example.com',
        amount: this.bookingData.price || 0,
        paymentMethod: 'CREDIT_CARD',
        cardNumber: this.cardForm.value.cardNumber,
        expiryDate: this.cardForm.value.expiryDate,
        cvv: this.cardForm.value.cvv
      };

      // Process payment through Payment-MS directly
      this.paymentService.processPayment(paymentRequest).subscribe({
        next: (paymentResponse) => {
          console.log('Payment processed successfully:', paymentResponse);
          
          if (paymentResponse.status === 'SUCCESS') {
            // Create booking after successful payment
            this.bookingService.createBooking(this.bookingData).subscribe({
              next: (bookingResponse) => {
                console.log('Booking successful after payment:', bookingResponse);
                this.isProcessing = false;
                this.router.navigate(['/confirmation'], { 
                  queryParams: { 
                    bookingId: bookingResponse.bookingId || '12345',
                    paymentId: paymentResponse.paymentId 
                  },
                  state: { 
                    booking: this.bookingData 
                  }
                });
              },
              error: (error) => {
                console.error('Booking failed after successful payment:', error);
                this.isProcessing = false;
                // Show confirmation anyway with payment details
                this.router.navigate(['/confirmation'], { 
                  queryParams: { 
                    bookingId: 'MANUAL_' + Date.now(),
                    paymentId: paymentResponse.paymentId 
                  },
                  state: { 
                    booking: this.bookingData 
                  }
                });
              }
            });
          } else {
            console.error('Payment failed:', paymentResponse.message);
            this.isProcessing = false;
            alert('Payment failed: ' + paymentResponse.message);
          }
        },
        error: (error) => {
          console.error('Payment service error:', error);
          this.isProcessing = false;
          
          // Fallback to Option 2: Use the existing Kafka flow via BookingService
          console.log('Falling back to Kafka-based payment flow...');
          this.processPaymentViaBooking();
        }
      });
    } else {
      // For non-card payments, use the existing Kafka flow
      this.processPaymentViaBooking();
    }
  }

  // Option 2: Process payment via Booking-MS (existing Kafka flow)
  private processPaymentViaBooking(): void {
    console.log('=== PAYMENT VIA BOOKING DEBUG ===');
    console.log('Starting payment via booking service...');
    console.log('Booking data to be sent:', this.bookingData);
    
    setTimeout(() => {
      // Create booking which will trigger Kafka payment flow
      console.log('Calling bookingService.createBooking...');
      this.bookingService.createBooking(this.bookingData).subscribe({
        next: (response) => {
          console.log('✅ Booking successful with Kafka payment flow:', response);
          this.isProcessing = false;
          alert(`✅ Booking created successfully! Booking ID: ${response.bookingId || 'Generated'}`);
          this.router.navigate(['/confirmation'], { 
            queryParams: { bookingId: response.bookingId || '12345' },
            state: { 
              booking: this.bookingData 
            }
          });
        },
        error: (error) => {
          console.error('❌ Booking failed:', error);
          console.error('Error details:', {
            status: error.status,
            statusText: error.statusText,
            message: error.message,
            error: error.error
          });
          this.isProcessing = false;
          
          // Show detailed error to user
          let errorMessage = 'Booking failed: ';
          if (error.status === 0) {
            errorMessage += 'Unable to connect to server. Please check if services are running.';
          } else if (error.status === 404) {
            errorMessage += 'Booking service not found. Please check API configuration.';
          } else if (error.status >= 500) {
            errorMessage += 'Server error. Please try again later.';
          } else {
            errorMessage += error.error?.message || error.message || 'Unknown error occurred.';
          }
          
          alert(errorMessage);
          
          // For demo purposes, still show confirmation with mock data
          // const mockBookingId = Date.now().toString();
          // this.router.navigate(['/confirmation'], { 
          //   queryParams: { bookingId: mockBookingId },
          //   state: { 
          //     booking: this.bookingData 
          //   }
          // });
        }
      });
    }, 2000); // Simulate 2-second payment processing
  }

  goBack(): void {
    this.router.navigate(['/booking']);
  }
  
  // Helper method to format card number with spaces
  formatCardNumber(event: any): void {
    let value = event.target.value;
    
    // Update form control with the value as-is
    this.cardForm.patchValue({ cardNumber: value });
    
    // Keep the display value as entered by user
    event.target.value = value;
  }
  
  // Helper method to format expiry date
  formatExpiryDate(event: any): void {
    let value = event.target.value.replace(/\D/g, '');
    if (value.length >= 2) {
      value = value.substring(0, 2) + '/' + value.substring(2, 4);
    }
    event.target.value = value;
    this.cardForm.patchValue({ expiryDate: value });
  }

  goBackToBooking(): void {
    this.router.navigate(['/booking']);
  }

  generateBookingId(): string {
    return 'BK' + Date.now();
  }
}
