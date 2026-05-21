import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { KorisnikDTO } from '../../models/KorisnikDTO';
import { AuthService } from '../../services/auth';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class RegisterComponent {
  korisnik: KorisnikDTO = {
    id: 0,
    ime: '',
    prezime: '',
    username: '',
    email: '',
    password: '',
    pol: '',
    datumRodjenja: ''
  };

  message = '';
  error = '';
  loading = false;

  constructor(private authService: AuthService, private router: Router) {}

  register() {
    this.loading = true;
    this.message = '';
    this.error = '';

    this.authService.register(this.korisnik).subscribe({
      next: (res) => {
        this.message = res.message || 'Uspešno ste se registrovali!';
        this.loading = false;

        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 1500);
      },
      error: (err) => {
        this.error = err.error?.error || 'Greška pri registraciji.';
        this.loading = false;
      }
    });
  }
}
