import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user';
import { AuthService } from '../../services/auth';
import { RezultatDTO } from '../../models/RezultatDTO';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-rezultati',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rezultati.html',
  styleUrl: './rezultati.css',
})
export class RezultatiComponent implements OnInit {
  rezultati: RezultatDTO[] = [];
  loading = true;

  constructor(
    private userService: UserService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    const user = this.authService.user();
    if (!user || user.id === undefined) {
      console.error('Korisnik nije ulogovan ili nema ID');
      this.loading = false;
      return;
    }

    this.userService.getResults(user.id).subscribe({
      next: (response) => {
        this.rezultati = response.rezultati;
        this.loading = false;
      },
      error: (err) => {
        console.error('Greška prilikom učitavanja rezultata:', err);
        this.loading = false;
      }
    });
  }
}
