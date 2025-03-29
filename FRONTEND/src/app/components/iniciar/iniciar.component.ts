import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-iniciar',
  templateUrl: './iniciar.component.html',
  styleUrls: ['./iniciar.component.css'],
  standalone: true,
  imports: [FormsModule, CommonModule]
})
export class IniciarComponent {
  username: string = '';
  password: string = '';
  isLoading: boolean = false;
  loginError: string = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
    // Redirigir si ya está autenticado
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['/inicio']);
    }
  }

  login(): void {
    if (!this.username || !this.password) {
      this.loginError = 'Por favor ingrese nombre de usuario y contraseña';
      return;
    }

    this.isLoading = true;
    this.loginError = '';

    this.authService.login(this.username, this.password).subscribe({
      next: () => {
        // La redirección se maneja en el servicio de autenticación
        this.isLoading = false;
      },
      error: (error) => {
        this.isLoading = false;
        this.loginError = error.error?.message || 'Error al iniciar sesión. Verifique sus credenciales.';
      }
    });
  }
}
