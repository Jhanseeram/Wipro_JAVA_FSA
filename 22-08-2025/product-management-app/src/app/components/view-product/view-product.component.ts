import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-view-product',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './view-product.component.html',
  styleUrl: './view-product.component.css'
})
export class ViewProductComponent implements OnInit {
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
}
