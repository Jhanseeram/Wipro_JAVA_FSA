import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  template: `
    <div class="container">
      <h2>{{isEditMode ? 'Edit' : 'Add'}} Product</h2>
      
      <form (ngSubmit)="onSubmit()" #productForm="ngForm">
        <div class="form-group">
          <label for="name">Product Name:</label>
          <input 
            type="text" 
            id="name" 
            name="name" 
            [(ngModel)]="product.name" 
            required 
            #name="ngModel"
            class="form-control">
          <div *ngIf="name.invalid && name.touched" class="error">
            Product name is required.
          </div>
        </div>

        <div class="form-group">
          <label for="category">Category:</label>
          <select 
            id="category" 
            name="category" 
            [(ngModel)]="product.category" 
            required 
            #category="ngModel"
            class="form-control">
            <option value="">Select Category</option>
            <option value="Electronics">Electronics</option>
            <option value="Furniture">Furniture</option>
            <option value="Stationery">Stationery</option>
            <option value="Clothing">Clothing</option>
            <option value="Books">Books</option>
          </select>
          <div *ngIf="category.invalid && category.touched" class="error">
            Category is required.
          </div>
        </div>

        <div class="form-group">
          <label for="price">Price:</label>
          <input 
            type="number" 
            id="price" 
            name="price" 
            [(ngModel)]="product.price" 
            required 
            min="0" 
            step="0.01" 
            #price="ngModel"
            class="form-control">
          <div *ngIf="price.invalid && price.touched" class="error">
            Price is required and must be greater than 0.
          </div>
        </div>

        <div class="form-actions">
          <button type="submit" [disabled]="!productForm.form.valid" class="btn btn-primary">
            {{isEditMode ? 'Update' : 'Add'}} Product
          </button>
          <button type="button" (click)="cancel()" class="btn btn-secondary">
            Cancel
          </button>
        </div>
      </form>
    </div>
  `,
  styles: [`
    .container {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
    }
    .form-group {
      margin-bottom: 20px;
    }
    label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
    }
    .form-control {
      width: 100%;
      padding: 10px;
      border: 1px solid #ddd;
      border-radius: 4px;
      font-size: 16px;
    }
    .form-control:focus {
      border-color: #007bff;
      outline: none;
    }
    .error {
      color: #dc3545;
      font-size: 14px;
      margin-top: 5px;
    }
    .form-actions {
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
    .btn:disabled {
      opacity: 0.6;
      cursor: not-allowed;
    }
    .btn-primary { 
      background-color: #007bff; 
      color: white; 
    }
    .btn-secondary { 
      background-color: #6c757d; 
      color: white; 
    }
  `]
})
export class ProductFormComponent implements OnInit {
  product: Product = {
    name: '',
    category: '',
    price: 0
  };
  isEditMode = false;
  productId: number | null = null;

  constructor(
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEditMode = true;
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

  onSubmit(): void {
    if (this.isEditMode && this.productId) {
      this.productService.updateProduct(this.productId, this.product).subscribe({
        next: () => {
          this.router.navigate(['/products']);
        },
        error: (error) => {
          console.error('Error updating product:', error);
        }
      });
    } else {
      this.productService.createProduct(this.product).subscribe({
        next: () => {
          this.router.navigate(['/products']);
        },
        error: (error) => {
          console.error('Error creating product:', error);
        }
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/products']);
  }
}
