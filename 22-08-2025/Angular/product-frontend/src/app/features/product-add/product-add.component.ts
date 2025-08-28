import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';

@Component({
  selector: 'app-product-add',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './product-add.component.html'
})
export class ProductAddComponent {
  private productService = inject(ProductService);
  private router = inject(Router);
  product: Product = { name: '', category: '', price: 0, qty: 0 };

  save() {
    this.productService.create(this.product).subscribe(() => this.router.navigate(['/products']));
  }
}
