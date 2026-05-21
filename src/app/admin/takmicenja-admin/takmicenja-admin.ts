import { Component, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { AdminService } from '../../services/admin';
import { TakmicenjeDTO } from '../../models/TakmicenjeDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-takmicenja-admin',
  standalone: true,
  imports: [CommonModule, DatePipe],
  templateUrl: './takmicenja-admin.html',
  styleUrls: ['./takmicenja-admin.css']
})
export class TakmicenjaAdminComponent implements OnInit {

  takmicenja: TakmicenjeDTO[] = [];
  loading = true;
  success = '';
  error = '';

  constructor(
    private adminService: AdminService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadTakmicenja();
  }

  loadTakmicenja(): void {
    this.loading = true;
    this.adminService.getTakmicenja().subscribe({
      next: (data) => {
        this.takmicenja = data;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.error = 'Greška pri učitavanju takmičenja.';
        this.loading = false;
      }
    });
  }

  addNovo(): void {
    this.router.navigate(['/admin/takmicenja/novo']);
  }

  izmeni(id?: number): void {
    if (id != null) {
      this.router.navigate(['/admin/takmicenja/izmeni', id]);
    }
  }

  obrisi(id?: number): void {
    if (id != null && confirm('Da li ste sigurni da želite da obrišete takmičenje?')) {
      this.adminService.deleteTakmicenje(id).subscribe({
        next: () => {
          this.success = 'Takmičenje je obrisano.';
          this.loadTakmicenja();
        },
        error: (err) => {
          console.error(err);
          this.error = 'Greška pri brisanju takmičenja.';
        }
      });
    }
  }
}
