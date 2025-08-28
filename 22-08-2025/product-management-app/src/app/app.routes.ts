import { Routes } from '@angular/router';
import { ProductListComponent } from './components/product-list/product-list.component';
import { AddProductComponent } from './components/add-product/add-product.component';
import { EditProductComponent } from './components/edit-product/edit-product.component';
import { ViewProductComponent } from './components/view-product/view-product.component';
import { DeleteProductComponent } from './components/delete-product/delete-product.component';
import { ProductFormComponent } from './components/product-form/product-form.component';

export const routes: Routes = [
  { path: '', redirectTo: '/products', pathMatch: 'full' },
  { path: 'products', component: ProductListComponent },
  { path: 'products/add', component: AddProductComponent },
  { path: 'products/form/:id', component: ProductFormComponent }, // For editing via form
  { path: 'products/edit/:id', component: EditProductComponent },
  { path: 'products/view/:id', component: ViewProductComponent },
  { path: 'products/delete/:id', component: DeleteProductComponent },
  { path: '**', redirectTo: '/products' }
];
