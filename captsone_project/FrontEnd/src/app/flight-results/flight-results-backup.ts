import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { Flight } from './models/flight.interface';
import { FlightService } from '../services/flight.service';

@Component({
  selector: 'app-flight-results',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './flight-results.html',
  styleUrl: './flight-results.css'
})
export class FlightResults implements OnInit {
  // Search parameters
  fromLocation: string = '';
  toLocation: string = '';
  departureDay: string = '';
  departureMonthYear: string = '';
  departureWeekday: string = '';
  departureDate: string = '';
  tripType: string = 'oneway';
  
  // Applied filters
  appliedFilters: {
    nonStop: boolean;
  } = {
    nonStop: false
  };

  // Popular filters
  filters: Array<{
    name: string;
    value: boolean;
    price?: number;
  }> = [
    { name: 'Non Stop', value: false, price: 5650 },
    { name: 'IndiGo', value: false, price: 5650 },
    { name: 'Morning Departures', value: false, price: 6909 },
    { name: 'Late Departures', value: false, price: 5650 }
  ];

  // Flight results
  flights: Flight[] = [];
  
  // Mock data for fallback if API fails
  mockFlights: Flight[] = [
    {
      airline: 'IndiGo',
      flightNumber: '6E 234',
      departureTime: '18:00',
      departureCity: 'Kolkata',
      duration: '02h 15m',
      arrivalTime: '20:15',
      arrivalCity: 'Chennai',
      price: 5650,
      nonStop: true
    },
    {
      airline: 'IndiGo',
      flightNumber: '6E 723',
      departureTime: '21:00',
      departureCity: 'Kolkata',
      duration: '02h 15m',
      arrivalTime: '23:15',
      arrivalCity: 'Chennai',
      price: 5650,
      nonStop: true
    },
    {
      airline: 'Air India',
      flightNumber: 'AI 763',
      departureTime: '17:00',
      departureCity: 'Kolkata',
      duration: '02h 30m',
      arrivalTime: '19:30',
      arrivalCity: 'Chennai',
      price: 5651,
      nonStop: true
    },
    {
      airline: 'IndiGo',
      flightNumber: '6E 892',
      departureTime: '15:00',
      departureCity: 'Kolkata',
      duration: '02h 20m',
      arrivalTime: '17:20',
      arrivalCity: 'Chennai',
      price: 6385,
      nonStop: true
    },
    {
      airline: 'Air India',
      flightNumber: 'AI 567',
      departureTime: '09:30',
      departureCity: 'Kolkata',
      duration: '04h 45m',
      arrivalTime: '14:15',
      arrivalCity: 'Chennai',
      price: 4850,
      nonStop: false
    },
    {
      airline: 'IndiGo',
      flightNumber: '6E 445',
      departureTime: '11:45',
      departureCity: 'Kolkata',
      duration: '05h 30m',
      arrivalTime: '17:15',
      arrivalCity: 'Chennai',
      price: 4200,
      nonStop: false
    }
  ];
  
  // Initially filtered flights
  filteredFlights: Flight[] = [];

  constructor(
    private route: ActivatedRoute, 
    private router: Router,
    private flightService: FlightService
  ) { }

  ngOnInit(): void {
    // Get search parameters from URL
    this.route.queryParams.subscribe(params => {
      this.fromLocation = params['from'] || 'Kolkata';
      this.toLocation = params['to'] || 'Chennai';
      this.departureDay = params['day'] || '15';
      this.departureMonthYear = params['month'] || 'Aug\'24';
      this.departureWeekday = params['weekday'] || 'Thursday';
      this.tripType = params['tripType'] || 'oneway';
      
      // Format date for API call (YYYY-MM-DD)
      const year = this.departureMonthYear.split("'")[1];
      const month = this.getMonthNumber(this.departureMonthYear.split("'")[0].trim());
      this.departureDate = `20${year}-${month}-${this.departureDay}`;
      
      // Ensure filters are completely reset
      this.appliedFilters = { nonStop: false };
      this.filters.forEach(filter => filter.value = false);
      
      // Update flights based on search parameters
      this.updateFlightResults();
    });
  }
  
  // Get month number from month name
  private getMonthNumber(monthName: string): string {
    const months = {
      'Jan': '01', 'Feb': '02', 'Mar': '03', 'Apr': '04', 
      'May': '05', 'Jun': '06', 'Jul': '07', 'Aug': '08', 
      'Sep': '09', 'Oct': '10', 'Nov': '11', 'Dec': '12'
    };
    return months[monthName as keyof typeof months];
  }
  
  // Calculate display price based on trip type
  getDisplayPrice(basePrice: number): number {
    return this.tripType === 'round' ? basePrice * 2 : basePrice;
  }
  
  // Get trip type display text
  getTripTypeText(): string {
    return this.tripType === 'round' ? 'Round Trip' : 'One Way';
  }
  
  // Update flight results based on search parameters
  updateFlightResults(): void {
    // Fetch flights from the Flight Microservice
    this.flightService.getFlightsByRouteAndDate(
      this.fromLocation, 
      this.toLocation, 
      this.departureDate
    ).subscribe({
      next: (data) => {
        this.flights = data;
        this.filteredFlights = [...this.flights]; // Show all flights initially
      },
      error: (err) => {
        console.error('Error fetching flights:', err);
        // Fallback to mock data if API fails
        this.flights = this.mockFlights.filter(flight => 
          flight.departureCity.toLowerCase() === this.fromLocation.toLowerCase() &&
          flight.arrivalCity.toLowerCase() === this.toLocation.toLowerCase()
        );
        this.filteredFlights = [...this.flights]; // Show all flights initially
      }
    });
  }

  // Check if any filters are currently active
  private hasActiveFilters(): boolean {
    return this.appliedFilters.nonStop === true || 
           this.filters.some(filter => filter.value === true);
  }

  // Apply selected filters to flight list
  applyFilters(): void {
    this.filteredFlights = this.flights.filter(flight => {
      // Apply non-stop filter only if it's explicitly enabled
      if (this.appliedFilters.nonStop === true && !flight.nonStop) {
        return false;
      }
      
      // Apply airline filter
      const indigoFilter = this.filters.find(f => f.name === 'IndiGo');
      if (indigoFilter?.value === true && flight.airline !== 'IndiGo') {
        return false;
      }
      
      // Apply morning departures filter (before 12:00)
      const morningFilter = this.filters.find(f => f.name === 'Morning Departures');
      if (morningFilter?.value === true) {
        const hour = parseInt(flight.departureTime.split(':')[0]);
        if (hour >= 12) return false;
      }
      
      // Apply late departures filter (after 18:00)
      const lateFilter = this.filters.find(f => f.name === 'Late Departures');
      if (lateFilter?.value === true) {
        const hour = parseInt(flight.departureTime.split(':')[0]);
        if (hour < 18) return false;
      }
      
      return true;
    });
  }

  // Toggle a filter
  toggleFilter(filterName: string): void {
    const filter = this.filters.find(f => f.name === filterName);
    if (filter) {
      filter.value = !filter.value;
      
      // Update applied filters
      if (filterName === 'Non Stop') {
        this.appliedFilters.nonStop = filter.value;
      }
      
      // Apply filters immediately when user toggles them
      this.applyFilters();
    }
  }

  // Clear all filters
  clearAllFilters(): void {
    this.filters.forEach(filter => filter.value = false);
    this.appliedFilters = { nonStop: false };
    this.applyFilters();
  }

  // Book a flight
  bookFlight(flight: Flight): void {
    this.router.navigate(['/booking'], { queryParams: { flightId: flight.flightNumber } });
  }
}
