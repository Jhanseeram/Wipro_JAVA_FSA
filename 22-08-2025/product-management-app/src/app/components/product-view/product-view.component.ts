import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-product-view',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <div class="container">
      <h2>Product Details</h2>
      
      <div *ngIf="product" class="product-details">
        <div class="detail-row">
          <label>ID:</label>
          <span>{{product.id}}</span>
        </div>
        
        <div class="detail-row">
          <label>Name:</label>
          <span>{{product.name}}</span>
        </div>
        
        <div class="detail-row">
          <label>Category:</label>
          <span>{{product.category}}</span>
        </div>
        
        <div class="detail-row">
          <label>Price:</label>
          <span>\${{product.price}}</span>
        </div>
      </div>
      
      <div *ngIf="!product" class="loading">
        Loading product details...
      </div>
      
      <div class="actions">
        <button class="btn btn-warning" [routerLink]="['/products/edit', product?.id]">
          Edit Product
        </button>
        <button class="btn btn-secondary" [routerLink]="['/products']">
          Back to List
        </button>
      </div>
    </div>
  `,
  styles: [`
    .container {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
    }
    .product-details {
      background-color: #f8f9fa;
      padding: 20px;
      border-radius: 8px;
      margin-bottom: 20px;
    }
    .detail-row {
      display: flex;
      margin-bottom: 15px;
      align-items: center;
    }
    .detail-row label {
      font-weight: bold;
      min-width: 100px;
      margin-right: 15px;
    }
    .detail-row span {
      font-size: 16px;
    }
    .loading {
      text-align: center;
      padding: 40px;
      color: #666;
    }
    .actions {
      margin-top: 30px;
    }
    .btn {
      padding: 10px 20px;
      margin-right: 10px;
      border: none;
      cursor: pointer;
      text-decoration: none;
      display: inline-block;
      border-radius: 4px;
      font-size: 16px;
    }
    .btn-warning { 
      background-color: #ffc107; 
      color: black; 
    }
    .btn-secondary { 
      background-color: #6c757d; 
      color: white; 
    }
  `]
})
export class ProductViewComponent implements OnInit {
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
}
