import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent {
  product: Product = {
    name: '',
    category: '',
    price: 0
  };

  constructor(
    private productService: ProductService,
    private router: Router
  ) { }

  onSubmit(): void {
    this.productService.createProduct(this.product).subscribe({
      next: () => {
        console.log('Product added successfully');
        this.router.navigate(['/products']);
      },
      error: (error) => {
        console.error('Error adding product:', error);
        alert('Error adding product. Please try again.');
      }
    });
  }

  cancel(): void {
    this.router.navigate(['/products']);
  }
}
