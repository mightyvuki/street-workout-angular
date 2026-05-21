import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent {
  username = '';
  password = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    this.authService.login({ username: this.username, password: this.password }).subscribe({
      next: () => this.router.navigate(['/home']),
      error: () => this.errorMessage = 'Neuspešna prijava. Proveri podatke.'
    });
  }
  submit(form?: NgForm) {
    if (form && form.invalid) {
      form.control.markAllAsTouched();
      return;
    }

    this.authService.login({ username: this.username, password: this.password }).subscribe({
      next: (res) => {
        const uloga = res.user.uloga;
        alert(`Uspešno ste prijavljeni kao ${uloga}`);

        if (uloga === 'ADMIN') {
          this.router.navigate(['/adminHome']);
        } else {
          this.router.navigate(['/home']);
        }
      },
      error: () => alert('Neuspešna prijava!')
    });
  }
}
