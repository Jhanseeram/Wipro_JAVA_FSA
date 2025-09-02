/**
 * Interface representing an airport
 */
export interface Airport {
  /**
   * The IATA airport code (e.g., LAX, JFK)
   */
  code: string;
  
  /**
   * The full name of the airport
   */
  name: string;
  
  /**
   * The city where the airport is located
   */
  city: string;
  
  /**
   * The country where the airport is located
   */
  country: string;
}
