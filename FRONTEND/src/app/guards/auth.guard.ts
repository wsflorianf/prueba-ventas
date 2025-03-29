import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  
  // This will check token existence and expiration
  if (authService.isLoggedIn()) {
    return true;
  }
  
  // Redirect to login page if not authenticated or token expired
  router.navigate(['/sesion']);
  return false;
};
