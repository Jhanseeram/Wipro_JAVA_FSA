import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

export interface BookingData {
  bookingId?: string | null;
  bookingDate?: string;
  noOfPassengers?: number;
  passengerName?: string;
  email?: string;
  phone?: string;
  flightId?: string;
  fromLocation?: string;
  toLocation?: string;
  price?: number;
}

export interface FlightSearchData {
  from?: string;
  to?: string;
  travelDate?: string;
  passengers?: number;
}

export interface FlightData {
  flightNumber?: string;
  airlineName?: string;
  airlineCode?: string;
  aircraftType?: string;
  departureTime?: string;
  arrivalTime?: string;
  duration?: string;
  price?: number;
  basePrice?: number;
  departure?: {
    city?: string;
    code?: string;
    airport?: string;
  };
  arrival?: {
    city?: string;
    code?: string;
    airport?: string;
  };
  departureDate?: string;
  flightType?: string;
}

@Injectable({
  providedIn: 'root'
})
export class DataSharingService {
  private bookingDataSubject = new BehaviorSubject<BookingData | null>(null);
  public bookingData$ = this.bookingDataSubject.asObservable();
  
  private selectedFlightSubject = new BehaviorSubject<FlightData | null>(null);
  public selectedFlight$ = this.selectedFlightSubject.asObservable();
  
  private flightSearchDataSubject = new BehaviorSubject<FlightSearchData | null>(null);
  public flightSearchData$ = this.flightSearchDataSubject.asObservable();

  constructor() { }

  setBookingData(data: BookingData): void {
    console.log('DataSharingService: Setting booking data:', data);
    this.bookingDataSubject.next(data);
    
    // Also store in sessionStorage as a backup
    sessionStorage.setItem('bookingData', JSON.stringify(data));
  }

  getBookingData(): BookingData | null {
    const currentData = this.bookingDataSubject.value;
    
    if (currentData) {
      return currentData;
    }
    
    // Fallback: Try to get from sessionStorage
    try {
      const storedData = sessionStorage.getItem('bookingData');
      if (storedData) {
        const parsedData = JSON.parse(storedData);
        console.log('DataSharingService: Retrieved booking data from sessionStorage:', parsedData);
        this.bookingDataSubject.next(parsedData);
        return parsedData;
      }
    } catch (error) {
      console.error('Error retrieving booking data from sessionStorage:', error);
    }
    
    return null;
  }

  clearBookingData(): void {
    console.log('DataSharingService: Clearing booking data');
    this.bookingDataSubject.next(null);
    sessionStorage.removeItem('bookingData');
  }

  hasBookingData(): boolean {
    return this.getBookingData() !== null;
  }

  // Flight selection methods
  setSelectedFlight(flight: FlightData): void {
    console.log('DataSharingService: Setting selected flight:', flight);
    this.selectedFlightSubject.next(flight);
    sessionStorage.setItem('selectedFlight', JSON.stringify(flight));
  }

  getSelectedFlight(): FlightData | null {
    const currentFlight = this.selectedFlightSubject.value;
    
    if (currentFlight) {
      return currentFlight;
    }
    
    // Fallback: Try to get from sessionStorage
    try {
      const storedFlight = sessionStorage.getItem('selectedFlight');
      if (storedFlight) {
        const parsedFlight = JSON.parse(storedFlight);
        console.log('DataSharingService: Retrieved selected flight from sessionStorage:', parsedFlight);
        this.selectedFlightSubject.next(parsedFlight);
        return parsedFlight;
      }
    } catch (error) {
      console.error('Error retrieving selected flight from sessionStorage:', error);
    }
    
    return null;
  }

  // Flight search data methods
  setFlightSearchData(searchData: FlightSearchData): void {
    console.log('DataSharingService: Setting flight search data:', searchData);
    this.flightSearchDataSubject.next(searchData);
    sessionStorage.setItem('flightSearchData', JSON.stringify(searchData));
  }

  getFlightSearchData(): FlightSearchData | null {
    const currentSearchData = this.flightSearchDataSubject.value;
    
    if (currentSearchData) {
      return currentSearchData;
    }
    
    // Fallback: Try to get from sessionStorage
    try {
      const storedSearchData = sessionStorage.getItem('flightSearchData');
      if (storedSearchData) {
        const parsedSearchData = JSON.parse(storedSearchData);
        console.log('DataSharingService: Retrieved flight search data from sessionStorage:', parsedSearchData);
        this.flightSearchDataSubject.next(parsedSearchData);
        return parsedSearchData;
      }
    } catch (error) {
      console.error('Error retrieving flight search data from sessionStorage:', error);
    }
    
    return null;
  }

  clearAllData(): void {
    console.log('DataSharingService: Clearing all data');
    this.bookingDataSubject.next(null);
    this.selectedFlightSubject.next(null);
    this.flightSearchDataSubject.next(null);
    sessionStorage.removeItem('bookingData');
    sessionStorage.removeItem('selectedFlight');
    sessionStorage.removeItem('flightSearchData');
  }
}