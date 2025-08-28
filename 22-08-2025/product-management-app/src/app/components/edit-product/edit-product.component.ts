import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-edit-product',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './edit-product.component.html',
  styleUrl: './edit-product.component.css'
})
export class EditProductComponent implements OnInit {
  product: Product | null = null;
  productId: number | null = null;

  constructor(
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.productId = +params['id']; // Convert string to number
        this.loadProduct();
      } else {
        this.router.navigate(['/products']);
      }
    });
  }

  loadProduct(): void {
    if (this.productId) {
      this.productService.getProduct(this.productId).subscribe({
        next: (data) => {
          this.product = data;
        },
        error: (error) => {
          this.router.navigate(['/products']);
        }
      });
    }
  }

  onSubmit(): void {
    if (this.productId && this.product) {
      this.productService.updateProduct(this.productId, this.product).subscribe({
        next: () => {
          console.log('Product updated successfully');
          this.router.navigate(['/products']);
        },
        error: (error) => {
          console.error('Error updating product:', error);
          alert('Error updating product. Please try again.');
        }
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/products']);
  }
}
