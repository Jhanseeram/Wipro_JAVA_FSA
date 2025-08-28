import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `
  <nav class="navbar navbar-dark bg-dark mb-4">
    <div class="container-fluid">
      <a class="navbar-brand" routerLink="/products">Product CRUD</a>
    </div>
  </nav>
  <div class="container mb-5">
    <router-outlet />
  </div>
  <footer class="text-center text-muted small py-3 border-top">&copy; {{ year }} Product CRUD</footer>
  `,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'product-frontend';
  year = new Date().getFullYear();
}
