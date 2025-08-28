import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-delete-product',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './delete-product.component.html',
  styleUrl: './delete-product.component.css'
})
export class DeleteProductComponent implements OnInit {
  product: Product | null = null;
  loading = false;

  constructor(
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loadProduct(+id); // Convert string to number
    } else {
      this.router.navigate(['/products']);
    }
  }

  loadProduct(id: number): void {
    this.loading = true;
    this.productService.getProduct(id).subscribe({
      next: (product) => {
        this.product = product;
        this.loading = false;
      },
      error: (error) => {
        this.loading = false;
        this.router.navigate(['/products']);
      }
    });
  }

  confirmDelete(): void {
    if (this.product?.id) {
      this.loading = true;
      this.productService.deleteProduct(this.product.id).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/products']);
        },
        error: (error) => {
          this.loading = false;
        }
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/products']);
  }
}
