import { Routes } from '@angular/router';
import { ProductListComponent } from './features/product-list/product-list.component';
import { ProductAddComponent } from './features/product-add/product-add.component';
import { ProductEditComponent } from './features/product-edit/product-edit.component';
import { ProductDeleteComponent } from './features/product-delete/product-delete.component';
import { PlaceOrderComponent } from './features/place-order/place-order.component';
import { OrderHistoryComponent } from './features/order-history/order-history.component';

export const routes: Routes = [
	{ path: '', redirectTo: 'products', pathMatch: 'full' },
	{ path: 'products', component: ProductListComponent },
	{ path: 'products/add', component: ProductAddComponent },
	{ path: 'products/edit/:id', component: ProductEditComponent },
	{ path: 'products/delete/:id', component: ProductDeleteComponent },
		{ path: 'orders/place', component: PlaceOrderComponent },
		{ path: 'orders', component: OrderHistoryComponent },
	{ path: '**', redirectTo: 'products' }
];
