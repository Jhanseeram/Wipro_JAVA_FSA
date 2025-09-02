import { Routes } from '@angular/router';
import { FlightSearch } from './flight-search/flight-search';
import { FlightResults } from './flight-results/flight-results';
import { BookingComponent } from './booking/booking';
import { PaymentComponent } from './payment/payment';
import { ConfirmationComponent } from './confirmation/confirmation';

export const routes: Routes = [
  { path: '', component: FlightSearch },
  { path: 'results', component: FlightResults },
  { path: 'booking', component: BookingComponent },
  { path: 'payment', component: PaymentComponent },
  { path: 'confirmation', component: ConfirmationComponent },
  { path: '**', redirectTo: '' }
];
