import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FlightService {
  private baseUrl = environment.flightServiceUrl;

  constructor(private http: HttpClient) { }

  searchFlights(searchRequest: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/search`, searchRequest);
  }

  getFlightById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getAllFlights(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);
  }

  // If your flight MS has additional endpoints, add them here
  getFlightsByRouteAndDate(sourceCity: string, destinationCity: string, departureDate: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/search`, {
      params: {
        sourceCity,
        destinationCity,
        departureDate
      }
    });
  }
}
