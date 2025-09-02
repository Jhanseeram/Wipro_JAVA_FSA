import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, CommonModule],
  template: `
    <!-- Navigation Header -->
    <header class="navbar-header">
      <nav class="navbar">
        <div class="container">
          <div class="nav-brand">
            <span class="brand-logo">✈️</span>
            <h1 class="brand-title">SkyBook</h1>
          </div>
          <div class="nav-links">
            <a routerLink="/" routerLinkActive="active">Home</a>
          </div>
        </div>
      </nav>
    </header>

    <!-- Main Content -->
    <main class="main-content">
      <router-outlet></router-outlet>
    </main>

    <!-- Footer -->
    <footer class="app-footer" *ngIf="showFooter">
      <div class="container">
        <div class="footer-content">
          <div class="footer-section">
            <h3>SkyBook</h3>
            <p>Your trusted flight booking partner</p>
            <div class="social-links">
              <a href="#" aria-label="Facebook">📘</a>
              <a href="#" aria-label="Twitter">🐦</a>
              <a href="#" aria-label="Instagram">📷</a>
            </div>
          </div>
          <div class="footer-section">
            <h4>Quick Links</h4>
            <ul>
              <li><a routerLink="/">Search Flights</a></li>
              <li><a routerLink="/booking">Book Now</a></li>
              <li><a href="#">Check Status</a></li>
              <li><a href="#">Manage Booking</a></li>
            </ul>
          </div>
          <div class="footer-section">
            <h4>Support</h4>
            <ul>
              <li><a href="#">Help Center</a></li>
              <li><a href="#">Contact Us</a></li>
              <li><a href="#">Terms & Conditions</a></li>
              <li><a href="#">Privacy Policy</a></li>
            </ul>
          </div>
          <div class="footer-section">
            <h4>Airlines</h4>
            <div class="airline-partners">
              <span class="airline-badge">IndiGo</span>
              <span class="airline-badge">Air India</span>
              <span class="airline-badge">SpiceJet</span>
              <span class="airline-badge">Vistara</span>
            </div>
          </div>
        </div>
        <div class="footer-bottom">
          <p>&copy; 2025 SkyBook Flight Booking. All rights reserved.</p>
        </div>
      </div>
    </footer>
  `,
  styles: [`
    /* Navigation Header */
    .navbar-header {
      background: linear-gradient(135deg, #1a73e8 0%, #007bff 100%);
      box-shadow: 0 2px 10px rgba(0,0,0,0.1);
      position: sticky;
      top: 0;
      z-index: 1000;
    }

    .navbar {
      padding: 1rem 0;
    }

    .container {
      max-width: 1200px;
      margin: 0 auto;
      padding: 0 1rem;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .nav-brand {
      display: flex;
      align-items: center;
      gap: 0.5rem;
    }

    .brand-logo {
      font-size: 2rem;
      animation: fly 3s ease-in-out infinite;
    }

    @keyframes fly {
      0%, 100% { transform: translateY(0px) rotate(0deg); }
      50% { transform: translateY(-5px) rotate(5deg); }
    }

    .brand-title {
      color: white;
      margin: 0;
      font-size: 1.8rem;
      font-weight: 700;
      text-shadow: 0 2px 4px rgba(0,0,0,0.2);
    }

    .nav-links {
      display: flex;
      gap: 1.5rem;
    }

    .nav-links a {
      color: rgba(255,255,255,0.9);
      text-decoration: none;
      padding: 0.5rem 1rem;
      border-radius: 25px;
      transition: all 0.3s ease;
      font-weight: 500;
    }

    .nav-links a:hover,
    .nav-links a.active {
      color: white;
      background-color: rgba(255,255,255,0.2);
      transform: translateY(-2px);
    }

    /* Main Content */
    .main-content {
      min-height: calc(100vh - 200px);
      padding-bottom: 2rem;
    }

    /* Footer */
    .app-footer {
      background: linear-gradient(135deg, #2c3e50 0%, #34495e 100%);
      color: white;
      margin-top: 3rem;
    }

    .footer-content {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
      gap: 2rem;
      padding: 3rem 0;
    }

    .footer-section h3,
    .footer-section h4 {
      margin-bottom: 1rem;
      color: #3498db;
    }

    .footer-section p {
      color: rgba(255,255,255,0.8);
      line-height: 1.6;
    }

    .footer-section ul {
      list-style: none;
      padding: 0;
    }

    .footer-section ul li {
      margin-bottom: 0.5rem;
    }

    .footer-section ul li a {
      color: rgba(255,255,255,0.7);
      text-decoration: none;
      transition: color 0.3s ease;
    }

    .footer-section ul li a:hover {
      color: #3498db;
    }

    .social-links {
      display: flex;
      gap: 1rem;
      margin-top: 1rem;
    }

    .social-links a {
      font-size: 1.5rem;
      text-decoration: none;
      transition: transform 0.3s ease;
    }

    .social-links a:hover {
      transform: scale(1.2);
    }

    .airline-partners {
      display: flex;
      flex-wrap: wrap;
      gap: 0.5rem;
    }

    .airline-badge {
      background: rgba(52, 152, 219, 0.2);
      color: #3498db;
      padding: 0.3rem 0.8rem;
      border-radius: 15px;
      font-size: 0.85rem;
      border: 1px solid rgba(52, 152, 219, 0.3);
    }

    .footer-bottom {
      border-top: 1px solid rgba(255,255,255,0.1);
      padding: 1.5rem 0;
      text-align: center;
      color: rgba(255,255,255,0.6);
    }

    /* Responsive Design */
    @media (max-width: 768px) {
      .nav-links {
        display: none;
      }

      .footer-content {
        grid-template-columns: 1fr;
        text-align: center;
      }

      .social-links,
      .airline-partners {
        justify-content: center;
      }
    }
  `]
})
export class App {
  title = 'SkyBook - Flight Booking System';

  get showFooter(): boolean {
    // You can add logic here to hide footer on certain pages if needed
    return true;
  }
}
