import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from '../services/booking.service';

@Component({
  standalone: true,
  selector: 'app-booking',
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './booking.html',
  styleUrl: './booking.css'
})
export class BookingComponent implements OnInit {
  // Flight details
  fromLocation: string = 'Kolkata';
  toLocation: string = 'Chennai';
  duration: string = '2h 30m';
  price: number = 5651;
  flightId: string = '';
  
  // Form and validation
  bookingForm!: FormGroup;
  isSubmitted = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private bookingService: BookingService,
    private formBuilder: FormBuilder
  ) { 
    this.initializeForm();
  }
  
  // Custom validators
  private nameValidator(control: AbstractControl): { [key: string]: any } | null {
    const name = control.value;
    if (!name) return null;
    
    // Name should only contain letters and spaces, minimum 2 characters
    const namePattern = /^[a-zA-Z\s]{2,50}$/;
    if (!namePattern.test(name)) {
      return { 'invalidName': { value: control.value } };
    }
    
    // Name should not start or end with space
    if (name.trim() !== name) {
      return { 'invalidName': { value: control.value } };
    }
    
    return null;
  }
  
  private phoneValidator(control: AbstractControl): { [key: string]: any } | null {
    const phone = control.value;
    if (!phone) return null;
    
    // Indian phone number: 10 digits, can start with 6-9
    const phonePattern = /^[6-9]\d{9}$/;
    if (!phonePattern.test(phone)) {
      return { 'invalidPhone': { value: control.value } };
    }
    
    return null;
  }
  
  private initializeForm(): void {
    this.bookingForm = this.formBuilder.group({
      noOfPassengers: [1, [
        Validators.required, 
        Validators.min(1), 
        Validators.max(4),
        Validators.pattern(/^[1-4]$/)
      ]],
      passengerName: ['', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50),
        this.nameValidator
      ]],
      email: ['', [
        Validators.required,
        Validators.email,
        Validators.maxLength(100)
      ]],
      phone: ['', [
        Validators.required,
        this.phoneValidator
      ]]
    });
  }
  
  // Getter methods for easy access to form controls
  get noOfPassengers() { return this.bookingForm.get('noOfPassengers')!; }
  get passengerName() { return this.bookingForm.get('passengerName')!; }
  get email() { return this.bookingForm.get('email')!; }
  get phone() { return this.bookingForm.get('phone')!; }
  
  // Get error messages for each field
  getPassengerNameErrors(): string {
    const control = this.passengerName;
    if (control.errors && (control.dirty || control.touched || this.isSubmitted)) {
      if (control.errors['required']) return 'Passenger name is required';
      if (control.errors['minlength']) return 'Name must be at least 2 characters long';
      if (control.errors['maxlength']) return 'Name cannot exceed 50 characters';
      if (control.errors['invalidName']) return 'Name should only contain letters and spaces';
    }
    return '';
  }
  
  getEmailErrors(): string {
    const control = this.email;
    if (control.errors && (control.dirty || control.touched || this.isSubmitted)) {
      if (control.errors['required']) return 'Email is required';
      if (control.errors['email']) return 'Please enter a valid email address';
      if (control.errors['maxlength']) return 'Email cannot exceed 100 characters';
    }
    return '';
  }
  
  getPhoneErrors(): string {
    const control = this.phone;
    if (control.errors && (control.dirty || control.touched || this.isSubmitted)) {
      if (control.errors['required']) return 'Phone number is required';
      if (control.errors['invalidPhone']) return 'Please enter a valid 10-digit Indian mobile number';
    }
    return '';
  }
  
  getPassengerCountErrors(): string {
    const control = this.noOfPassengers;
    if (control.errors && (control.dirty || control.touched || this.isSubmitted)) {
      if (control.errors['required']) return 'Number of passengers is required';
      if (control.errors['min'] || control.errors['max'] || control.errors['pattern']) {
        return 'Number of passengers must be between 1 and 4';
      }
    }
    return '';
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.flightId = params['flightId'] || '';
      
      // If we have a flight ID, fetch flight details
      if (this.flightId) {
        this.loadFlightDetails(this.flightId);
      }
    });
  }
  
  loadFlightDetails(flightId: string): void {
    // Create a FlightService and inject it if needed
    // For now, using static data as if fetched from a service
    // this.flightService.getFlightById(flightId).subscribe(flight => {
    //   this.fromLocation = flight.departureCity;
    //   this.toLocation = flight.arrivalCity;
    //   this.duration = flight.duration;
    //   this.price = flight.price;
    // });
  }

  proceedToPayment(): void {
    console.log('proceedToPayment called');
    this.isSubmitted = true;
    
    console.log('Form valid:', this.bookingForm.valid);
    console.log('Form value:', this.bookingForm.value);
    console.log('Form errors:', this.bookingForm.errors);
    
    // Check if form is valid
    if (!this.bookingForm.valid) {
      console.log('Form is invalid, marking all fields as touched');
      this.markAllFieldsAsTouched();
      return;
    }
    
    // Get form values
    const formValues = this.bookingForm.value;
    
    // Create booking data to pass to payment page
    const booking = {
      bookingId: null, // This will be generated by the backend
      bookingDate: new Date().toISOString().split('T')[0],
      noOfPassengers: formValues.noOfPassengers,
      passengerName: formValues.passengerName.trim(),
      email: formValues.email.trim().toLowerCase(),
      phone: formValues.phone,
      flightId: this.flightId,
      fromLocation: this.fromLocation,
      toLocation: this.toLocation,
      price: this.price
    };
    
    console.log('Proceeding to payment with booking data:', booking);
    
    try {
      // Navigate to payment page with booking data
      this.router.navigate(['/payment'], { 
        state: { booking: booking }
      }).then(success => {
        console.log('Navigation to payment successful:', success);
      }).catch(error => {
        console.error('Navigation to payment failed:', error);
      });
    } catch (error) {
      console.error('Error during navigation:', error);
    }
  }

  private markAllFieldsAsTouched(): void {
    Object.keys(this.bookingForm.controls).forEach(key => {
      this.bookingForm.get(key)?.markAsTouched();
    });
  }
}
