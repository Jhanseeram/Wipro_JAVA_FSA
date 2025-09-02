/**
 * Interface representing a day in the calendar
 */
export interface CalendarDay {
  /**
   * The day number (1-31) or null for empty cells
   */
  date: number | null;
  
  /**
   * Whether this day is currently selected
   */
  selected: boolean;
  
  /**
   * Whether this day is the current day
   */
  today: boolean;
  
  /**
   * Whether this day is in the past and should be disabled
   */
  isPastDate: boolean;
}
