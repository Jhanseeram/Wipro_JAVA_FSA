import { Component, OnInit, HostListener } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

// Import interfaces and data from separate files
import { CalendarDay } from './models/calendar-day.interface';
import { Airport } from './models/airport.interface';
import { AIRPORTS } from './models/airports.data';

@Component({
  selector: 'app-flight-search',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './flight-search.html',
  styleUrl: './flight-search.css'
})
export class FlightSearch implements OnInit {
  fromLocation: string = 'Kolkata';
  toLocation: string = 'Chennai';
  fromLocationDetails: string = 'CCU, Netaji Subhash Chandra Bose International Airport';
  toLocationDetails: string = 'MAA, Chennai International Airport (Meenambakkam)';
  
  departureDay: string = '2';
  departureMonthYear: string = 'Sep\'25';
  departureWeekday: string = 'Tuesday';
  
  // Trip Type
  tripType: 'round' | 'oneway' = 'round';
  
  // Validation
  showValidationMessage: boolean = false;
  validationMessage: string = '';
  
  // Dropdown states
  showFromDropdown: boolean = false;
  showToDropdown: boolean = false;
  filteredFromAirports: Airport[] = [];
  filteredToAirports: Airport[] = [];
  
  // Airport list from imported data
  airports: Airport[] = AIRPORTS;
  
  // Calendar properties
  showCalendar: boolean = false;
  currentDate: Date = new Date();
  selectedDate: Date = new Date(); // Initialize with current date
  calendarMonth: string = '';
  calendarYear: number = 0;
  calendarDays: CalendarDay[] = [];
  weekdays: string[] = ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'];
  monthNames: string[] = [
    'January', 'February', 'March', 'April', 'May', 'June',
    'July', 'August', 'September', 'October', 'November', 'December'
  ];
  
  constructor(private router: Router) {}
  
  ngOnInit(): void {
    this.initCalendar();
    // Initialize filtered airports
    this.filteredFromAirports = [...this.airports];
    this.filteredToAirports = [...this.airports];
  }

  // Close dropdowns and calendar when clicking outside
  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent): void {
    const clickedElement = event.target as HTMLElement;
    
    if (this.showCalendar && !clickedElement.closest('.date-display') && !clickedElement.closest('.calendar-popup')) {
      this.showCalendar = false;
    }
    
    if (this.showFromDropdown && !clickedElement.closest('.from-location-container') && !clickedElement.closest('.airport-dropdown')) {
      this.showFromDropdown = false;
    }
    
    if (this.showToDropdown && !clickedElement.closest('.to-location-container') && !clickedElement.closest('.airport-dropdown')) {
      this.showToDropdown = false;
    }
  }

  swapLocations(): void {
    const tempLocation = this.fromLocation;
    const tempDetails = this.fromLocationDetails;
    
    this.fromLocation = this.toLocation;
    this.fromLocationDetails = this.toLocationDetails;
    
    this.toLocation = tempLocation;
    this.toLocationDetails = tempDetails;
  }

  // Trip Type Selection
  selectTripType(type: 'round' | 'oneway'): void {
    this.tripType = type;
    this.hideValidationMessage();
  }

  // Validation Methods
  validateLocations(): boolean {
    if (this.fromLocation.toLowerCase() === this.toLocation.toLowerCase()) {
      this.showValidationMessage = true;
      this.validationMessage = 'From and To locations cannot be the same. Please select different destinations.';
      return false;
    }
    this.hideValidationMessage();
    return true;
  }

  hideValidationMessage(): void {
    this.showValidationMessage = false;
    this.validationMessage = '';
  }
  
  onFromLocationInput(): void {
    // Filter airports based on input
    const searchTerm = this.fromLocation.toLowerCase();
    this.filteredFromAirports = this.airports.filter(airport => 
      airport.code.toLowerCase().includes(searchTerm) || 
      airport.name.toLowerCase().includes(searchTerm) || 
      airport.city.toLowerCase().includes(searchTerm) || 
      airport.country.toLowerCase().includes(searchTerm)
    );
    
    // Show dropdown if we have input
    this.showFromDropdown = searchTerm.length > 0;
    
    // Update details if there's no dropdown selection
    if (searchTerm.length >= 3) {
      const matchingAirport = this.airports.find(airport => 
        airport.code.toLowerCase() === searchTerm || 
        airport.city.toLowerCase() === searchTerm
      );
      
      if (matchingAirport) {
        this.fromLocationDetails = `${matchingAirport.code}, ${matchingAirport.name}`;
      } else {
        this.fromLocationDetails = 'Type at least 3 characters';
      }
    } else {
      this.fromLocationDetails = 'Type at least 3 characters';
    }
    
    // Validate locations after input change
    this.validateLocations();
  }
  
  onToLocationInput(): void {
    // Filter airports based on input
    const searchTerm = this.toLocation.toLowerCase();
    this.filteredToAirports = this.airports.filter(airport => 
      airport.code.toLowerCase().includes(searchTerm) || 
      airport.name.toLowerCase().includes(searchTerm) || 
      airport.city.toLowerCase().includes(searchTerm) || 
      airport.country.toLowerCase().includes(searchTerm)
    );
    
    // Show dropdown if we have input
    this.showToDropdown = searchTerm.length > 0;
    
    // Update details if there's no dropdown selection
    if (searchTerm.length >= 3) {
      const matchingAirport = this.airports.find(airport => 
        airport.code.toLowerCase() === searchTerm || 
        airport.city.toLowerCase() === searchTerm
      );
      
      if (matchingAirport) {
        this.toLocationDetails = `${matchingAirport.code}, ${matchingAirport.name}`;
      } else {
        this.toLocationDetails = 'Type at least 3 characters';
      }
    } else {
      this.toLocationDetails = 'Type at least 3 characters';
    }
    
    // Validate locations after input change
    this.validateLocations();
  }
  
  selectFromAirport(airport: Airport): void {
    this.fromLocation = airport.city;
    this.fromLocationDetails = `${airport.code}, ${airport.name}`;
    this.showFromDropdown = false;
    this.validateLocations();
  }
  
  selectToAirport(airport: Airport): void {
    this.toLocation = airport.city;
    this.toLocationDetails = `${airport.code}, ${airport.name}`;
    this.showToDropdown = false;
    this.validateLocations();
  }
  
  toggleFromDropdown(): void {
    this.showFromDropdown = !this.showFromDropdown;
    if (this.showFromDropdown) {
      this.showToDropdown = false;
      this.showCalendar = false;
    }
  }
  
  toggleToDropdown(): void {
    this.showToDropdown = !this.showToDropdown;
    if (this.showToDropdown) {
      this.showFromDropdown = false;
      this.showCalendar = false;
    }
  }
  
  searchFlights(): void {
    // Validate before searching
    if (!this.validateLocations()) {
      return; // Don't proceed if validation fails
    }
    
    // Navigate to the results page with search parameters
    this.router.navigate(['/results'], {
      queryParams: {
        from: this.fromLocation,
        to: this.toLocation,
        day: this.departureDay,
        month: this.departureMonthYear,
        weekday: this.departureWeekday,
        tripType: this.tripType
      }
    });
  }

  // Calendar methods
  openCalendar(): void {
    this.showCalendar = !this.showCalendar;
    if (this.showCalendar) {
      this.generateCalendarDays(this.selectedDate.getFullYear(), this.selectedDate.getMonth());
    }
  }

  initCalendar(): void {
    this.calendarMonth = this.monthNames[this.selectedDate.getMonth()];
    this.calendarYear = this.selectedDate.getFullYear();
    this.generateCalendarDays(this.selectedDate.getFullYear(), this.selectedDate.getMonth());
  }

  generateCalendarDays(year: number, month: number): void {
    this.calendarMonth = this.monthNames[month];
    this.calendarYear = year;
    
    const firstDayOfMonth = new Date(year, month, 1).getDay();
    const daysInMonth = new Date(year, month + 1, 0).getDate();
    
    this.calendarDays = [];
    
    // Add empty cells for days before the first day of month
    for (let i = 0; i < firstDayOfMonth; i++) {
      this.calendarDays.push({ date: null, selected: false, today: false, isPastDate: false });
    }
    
    // Add days of the month
    for (let day = 1; day <= daysInMonth; day++) {
      const date = new Date(year, month, day);
      const today = this.isToday(date);
      const selected = this.isSameDate(date, this.selectedDate);
      const isPastDate = this.isPastDate(date);
      
      this.calendarDays.push({ 
        date: day, 
        selected: selected, 
        today: today,
        isPastDate: isPastDate
      });
    }
  }

  selectDate(day: CalendarDay): void {
    if (!day.date) return;
    
    const newDate = new Date(this.calendarYear, this.monthNames.indexOf(this.calendarMonth), day.date);
    
    // Prevent selecting past dates
    if (this.isPastDate(newDate)) {
      return;
    }
    
    this.selectedDate = newDate;
    
    // Update displayed date
    this.departureDay = day.date.toString();
    this.departureMonthYear = `${this.monthNames[newDate.getMonth()].substring(0, 3)}'${newDate.getFullYear().toString().substring(2)}`;
    this.departureWeekday = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][newDate.getDay()];
    
    // Update calendar
    this.generateCalendarDays(this.calendarYear, this.monthNames.indexOf(this.calendarMonth));
    
    // Close calendar
    this.showCalendar = false;
  }

  prevMonth(): void {
    let year = this.calendarYear;
    let month = this.monthNames.indexOf(this.calendarMonth) - 1;
    
    if (month < 0) {
      month = 11;
      year--;
    }
    
    this.generateCalendarDays(year, month);
  }

  nextMonth(): void {
    let year = this.calendarYear;
    let month = this.monthNames.indexOf(this.calendarMonth) + 1;
    
    if (month > 11) {
      month = 0;
      year++;
    }
    
    this.generateCalendarDays(year, month);
  }

  isToday(date: Date): boolean {
    const today = this.currentDate;
    return date.getDate() === today.getDate() &&
           date.getMonth() === today.getMonth() &&
           date.getFullYear() === today.getFullYear();
  }

  isPastDate(date: Date): boolean {
    const today = new Date();
    today.setHours(0, 0, 0, 0); // Reset time to start of day
    const compareDate = new Date(date);
    compareDate.setHours(0, 0, 0, 0); // Reset time to start of day
    return compareDate < today;
  }

  isSameDate(date1: Date, date2: Date): boolean {
    return date1.getDate() === date2.getDate() &&
           date1.getMonth() === date2.getMonth() &&
           date1.getFullYear() === date2.getFullYear();
  }
}
