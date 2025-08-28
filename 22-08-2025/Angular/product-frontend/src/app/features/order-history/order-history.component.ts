import { Component, OnInit, inject } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { OrderService } from '../../services/order.service';
import { Order } from '../../models/order';

@Component({
  selector: 'app-order-history',
  standalone: true,
  imports: [CommonModule, DatePipe],
  templateUrl: './order-history.component.html'
})
export class OrderHistoryComponent implements OnInit {
  private orderService = inject(OrderService);
  orders: Order[] = [];
  loading = true;

  ngOnInit() {
    this.orderService.history().subscribe({
      next: d => this.orders = d,
      complete: () => this.loading = false
    });
  }
}
