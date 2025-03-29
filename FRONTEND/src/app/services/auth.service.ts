import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

interface DecodedToken {
  exp: number;
  // Add other token claims you might need
  sub: string;
  // ... other fields
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = '127.0.0.1:8080/api/v1/auth';
  private tokenKey = 'auth_token';

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post(`http://${this.apiUrl}/login`, { username, password })
      .pipe(
        tap((response: any) => {
          if (response && response.token) {
            localStorage.setItem(this.tokenKey, response.token);
            this.router.navigate(['/inicio']);
          }
        })
      );
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
    this.router.navigate(['/sesion']);
  }

  isLoggedIn(): boolean {
    const token = this.getToken();
    
    // Check if token exists
    if (!token) {
      return false;
    }
    
    // Check if token is expired
    if (this.isTokenExpired(token)) {
      this.logout(); // Auto logout if token expired
      return false;
    }
    
    return true;
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  private isTokenExpired(token: string): boolean {
    try {
      const decoded: DecodedToken = jwtDecode(token);
      const currentTime = Date.now() / 1000; // Convert to seconds
      
      // Check if the token's expiration time is less than current time
      return decoded.exp < currentTime;
    } catch (error) {
      // If there's an error decoding the token, consider it expired
      console.error('Error decoding JWT token:', error);
      return true;
    }
  }
}
