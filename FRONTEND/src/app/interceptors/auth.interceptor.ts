import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const token = authService.getToken();

  // Si existe un token, lo añadimos al header
  if (token) {
    // Clonamos la solicitud y añadimos el header de autorización
    const authReq = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`)
    });
    
    // Continuamos con la solicitud modificada
    return next(authReq);
  }
  
  // Si no hay token, continuamos con la solicitud original
  return next(req);
};
