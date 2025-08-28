import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { OrderService } from '../../services/order.service';
import { Product } from '../../models/product';

@Component({
  selector: 'app-place-order',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './place-order.component.html'
})
export class PlaceOrderComponent implements OnInit {
  private productService = inject(ProductService);
  private orderService = inject(OrderService);
  products: Product[] = [];
  selectedId: number | null = null;
  qty: number | null = null;
  placing = false;
  message: string | null = null;
  error: string | null = null;

  ngOnInit() {
    this.productService.list().subscribe(p => this.products = p);
  }

  maxQty(): number { return (this.products.find(p => p.id === this.selectedId)?.qty) ?? 0; }

  place() {
    this.message = this.error = null;
    if (this.selectedId == null || this.qty == null) { this.error = 'Select product and quantity'; return; }
    if (this.qty <= 0) { this.error = 'Quantity must be > 0'; return; }
    if (this.qty > this.maxQty()) { this.error = 'Quantity exceeds available stock'; return; }
    this.placing = true;
    this.orderService.place(this.selectedId, this.qty).subscribe({
      next: o => { this.message = 'Order placed'; this.refreshProduct(o.product.id!); },
      error: err => this.error = err.error?.error || 'Failed',
      complete: () => this.placing = false
    });
  }

  private refreshProduct(id: number) {
    this.productService.get(id).subscribe(p => {
      const idx = this.products.findIndex(x => x.id === id);
      if (idx >= 0) this.products[idx] = p;
    });
  }
}
