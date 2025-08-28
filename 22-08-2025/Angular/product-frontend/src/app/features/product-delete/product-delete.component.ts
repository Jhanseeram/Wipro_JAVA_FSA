import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';

@Component({
  selector: 'app-product-delete',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './product-delete.component.html'
})
export class ProductDeleteComponent implements OnInit {
  private productService = inject(ProductService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  product: Product | null = null;
  id!: number;

  ngOnInit() {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.productService.get(this.id).subscribe(p => this.product = p);
  }

  remove() {
    this.productService.delete(this.id).subscribe(() => this.router.navigate(['/products']));
  }
}
