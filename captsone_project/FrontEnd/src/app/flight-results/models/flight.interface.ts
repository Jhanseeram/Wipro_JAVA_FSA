/**
 * Interface representing a flight result
 */
export interface Flight {
  /**
   * Airline name
   */
  airline: string;
  
  /**
   * Flight number
   */
  flightNumber: string;
  
  /**
   * Departure time
   */
  departureTime: string;
  
  /**
   * Departure city
   */
  departureCity: string;
  
  /**
   * Duration of flight
   */
  duration: string;
  
  /**
   * Arrival time
   */
  arrivalTime: string;
  
  /**
   * Arrival city
   */
  arrivalCity: string;
  
  /**
   * Price per adult
   */
  price: number;
  
  /**
   * Whether the flight is non-stop
   */
  nonStop: boolean;
}
