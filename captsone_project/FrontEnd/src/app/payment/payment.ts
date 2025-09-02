import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from '../services/booking.service';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './payment.html',
  styleUrl: './payment.css'
})
export class PaymentComponent implements OnInit {
  bookingData: any;
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
    private formBuilder: FormBuilder
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
    
    // Get booking data from route state
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras?.state) {
      this.bookingData = navigation.extras.state['booking'];
      console.log('Booking data received:', this.bookingData);
      
      // Pre-populate cardholder name from passenger details
      if (this.bookingData.passengerDetails && this.bookingData.passengerDetails.name) {
        this.cardForm.patchValue({
          cardholderName: this.bookingData.passengerDetails.name
        });
      } else if (this.bookingData.passengerName) {
        // Fallback to passengerName if passengerDetails not available
        this.cardForm.patchValue({
          cardholderName: this.bookingData.passengerName
        });
      }
    } else {
      console.log('No booking data found, using mock data');
      // Create mock booking data for testing
      this.bookingData = {
        fromLocation: 'Kolkata',
        toLocation: 'Chennai',
        passengerName: 'Test Passenger',
        noOfPassengers: 1,
        bookingDate: new Date().toISOString().split('T')[0],
        price: 5651
      };
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
    
    // Simulate payment processing
    setTimeout(() => {
      // Create booking after successful payment
      this.bookingService.createBooking(this.bookingData).subscribe({
        next: (response) => {
          console.log('Booking successful after payment:', response);
          this.isProcessing = false;
          // Navigate to confirmation page
          this.router.navigate(['/confirmation'], { 
            queryParams: { bookingId: response.bookingId || '12345' } 
          });
        },
        error: (error) => {
          console.error('Booking failed:', error);
          this.isProcessing = false;
          // For demo purposes, still show confirmation with mock data
          const mockBookingId = Date.now().toString();
          this.router.navigate(['/confirmation'], { 
            queryParams: { bookingId: mockBookingId } 
          });
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
}
