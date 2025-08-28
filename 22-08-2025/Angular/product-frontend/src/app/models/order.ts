import { Product } from './product';

export interface Order {
  id?: number;
  product: Product;
  qtyPurchased: number;
  orderDate: string;
}
