import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { environment } from '../../environments/environment';
import { Flight } from '../flight-results/models/flight.interface';

// Backend Flight entity interface
export interface BackendFlight {
  id: number;
  flightNumber: string;
  aircraftName: string;
  route: string;
  price: number;
  date: string;
}

@Injectable({
  providedIn: 'root'
})
export class FlightService {
  private baseUrl = environment.flightServiceUrl;

  constructor(private http: HttpClient) { }

  // Get all flights and map to frontend interface
  getAllFlights(): Observable<Flight[]> {
    return this.http.get<BackendFlight[]>(`${this.baseUrl}`).pipe(
      map(backendFlights => this.mapToFrontendFlights(backendFlights))
    );
  }

  // Search flights based on criteria
  searchFlights(searchRequest: any): Observable<Flight[]> {
    // Since Flight-MS only has basic CRUD, we'll get all flights and filter on frontend
    return this.getAllFlights();
  }

  getFlightById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  // Search flights by criteria - filtering on frontend since backend doesn't have search endpoint
  getFlightsByRouteAndDate(sourceCity: string, destinationCity: string, departureDate: string): Observable<Flight[]> {
    return this.getAllFlights().pipe(
      map(flights => flights.filter(flight => {
        const route = flight.departureCity + '-' + flight.arrivalCity;
        const searchRoute = sourceCity + '-' + destinationCity;
        return route.toLowerCase().includes(searchRoute.toLowerCase());
      }))
    );
  }

  // Map backend flights to frontend interface
  private mapToFrontendFlights(backendFlights: BackendFlight[]): Flight[] {
    console.log('Mapping backend flights:', backendFlights);
    
    return backendFlights.map(backendFlight => {
      // Parse route to get cities (assuming format like "Delhi-Mumbai")
      const routeParts = backendFlight.route.split('-');
      const departureCity = routeParts[0]?.trim() || 'Unknown';
      const arrivalCity = routeParts[1]?.trim() || 'Unknown';
      
      // Generate realistic flight times based on route
      const departureTime = this.generateDepartureTime();
      const arrivalTime = this.calculateArrivalTime(departureTime, departureCity, arrivalCity);
      const duration = this.calculateDuration(departureTime, arrivalTime);
      
      // Get airline from flight number if aircraftName is null
      const airline = this.getAirlineFromFlightNumber(backendFlight.flightNumber) || 
                     this.getAirlineFromAircraft(backendFlight.aircraftName);
      
      const mappedFlight = {
        airline: airline,
        flightNumber: backendFlight.flightNumber,
        departureTime: departureTime,
        departureCity: departureCity,
        duration: duration,
        arrivalTime: arrivalTime,
        arrivalCity: arrivalCity,
        price: backendFlight.price,
        nonStop: true // Assuming non-stop for simplicity
      };
      
      console.log('Mapped flight:', mappedFlight);
      return mappedFlight;
    });
  }

  // Get airline from flight number
  private getAirlineFromFlightNumber(flightNumber: string): string {
    if (flightNumber?.startsWith('6E')) {
      return 'IndiGo';
    } else if (flightNumber?.startsWith('AI')) {
      return 'Air India';
    } else if (flightNumber?.startsWith('SG')) {
      return 'SpiceJet';
    } else if (flightNumber?.startsWith('UK')) {
      return 'Vistara';
    } else if (flightNumber?.startsWith('9W')) {
      return 'Jet Airways';
    }
    return 'Unknown Airline';
  }

  // Helper methods for mapping
  private getAirlineFromAircraft(aircraftName: string): string {
    if (aircraftName?.toLowerCase().includes('indigo') || aircraftName?.toLowerCase().includes('6e')) {
      return 'IndiGo';
    } else if (aircraftName?.toLowerCase().includes('air india') || aircraftName?.toLowerCase().includes('ai')) {
      return 'Air India';
    } else if (aircraftName?.toLowerCase().includes('spicejet')) {
      return 'SpiceJet';
    } else {
      return aircraftName || 'Unknown Airline';
    }
  }

  private generateDepartureTime(): string {
    const hours = Math.floor(Math.random() * 16) + 6; // 6 AM to 10 PM
    const minutes = Math.random() < 0.5 ? '00' : '30';
    return `${hours.toString().padStart(2, '0')}:${minutes}`;
  }

  private calculateArrivalTime(departureTime: string, from: string, to: string): string {
    const [depHour, depMin] = departureTime.split(':').map(Number);
    const depMinutes = depHour * 60 + depMin;
    
    // Estimate flight duration based on cities (rough estimates)
    const flightDuration = this.estimateFlightDuration(from, to);
    const arrMinutes = depMinutes + flightDuration;
    
    const arrHour = Math.floor(arrMinutes / 60) % 24;
    const arrMin = arrMinutes % 60;
    
    return `${arrHour.toString().padStart(2, '0')}:${arrMin.toString().padStart(2, '0')}`;
  }

  private estimateFlightDuration(from: string, to: string): number {
    // Return duration in minutes (rough estimates for Indian cities)
    const routes: { [key: string]: number } = {
      'delhi-mumbai': 135,
      'mumbai-delhi': 135,
      'delhi-bangalore': 165,
      'bangalore-delhi': 165,
      'mumbai-bangalore': 105,
      'bangalore-mumbai': 105,
      'kolkata-chennai': 135,
      'chennai-kolkata': 135,
      'delhi-kolkata': 135,
      'kolkata-delhi': 135
    };
    
    const routeKey = `${from.toLowerCase()}-${to.toLowerCase()}`;
    return routes[routeKey] || 120; // Default 2 hours
  }

  private calculateDuration(departureTime: string, arrivalTime: string): string {
    const [depHour, depMin] = departureTime.split(':').map(Number);
    const [arrHour, arrMin] = arrivalTime.split(':').map(Number);
    
    const depMinutes = depHour * 60 + depMin;
    let arrMinutes = arrHour * 60 + arrMin;
    
    // Handle next day arrival
    if (arrMinutes < depMinutes) {
      arrMinutes += 24 * 60;
    }
    
    const durationMinutes = arrMinutes - depMinutes;
    const hours = Math.floor(durationMinutes / 60);
    const minutes = durationMinutes % 60;
    
    return `${hours.toString().padStart(2, '0')}h ${minutes.toString().padStart(2, '0')}m`;
  }
}
