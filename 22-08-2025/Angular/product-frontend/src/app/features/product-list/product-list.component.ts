import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './product-list.component.html'
})
export class ProductListComponent implements OnInit {
  private productService = inject(ProductService);
  products: Product[] = [];
  loading = true;
  error?: string;

  ngOnInit() {
    this.productService.list().subscribe({
      next: d => this.products = d,
      error: err => { this.error = err.message || 'Failed to load products'; },
      complete: () => this.loading = false
    });
  }
}
