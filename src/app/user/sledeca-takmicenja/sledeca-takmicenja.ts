import { Component, OnInit } from '@angular/core';
import { TakmicenjeService } from '../../services/takmicenje';
import { TakmicenjeDTO } from '../../models/TakmicenjeDTO';
import { UserService } from '../../services/user';
import { AuthService } from '../../services/auth';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sledeca-takmicenja',
  imports: [CommonModule],
  templateUrl: './sledeca-takmicenja.html',
  styleUrl: './sledeca-takmicenja.css',
})
export class SledecaTakmicenjaComponent implements OnInit {
  sledeca: TakmicenjeDTO[] = [];
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

  this.userService.getUpcomingCompetitions(user.id).subscribe({
    next: (response: { sledeca: TakmicenjeDTO[] }) => {
      this.sledeca = response.sledeca;
      this.loading = false;
    },
    error: (err) => {
      console.error('Greška prilikom učitavanja takmičenja:', err);
      this.loading = false;
    }
  });
}
}