import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TakmicenjeService } from '../../services/takmicenje';
import { PrijavaService } from '../../services/prijava';
import { AuthService } from '../../services/auth';
import { TakmicenjeDTO } from '../../models/TakmicenjeDTO';
import { PrijavaDTO } from '../../models/PrijavaDTO';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-najavljeno',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './najavljeno.html',
  styleUrls: ['./najavljeno.css']
})
export class NajavljenoComponent implements OnInit {
  takmicenje: TakmicenjeDTO | null = null;
  prijave: PrijavaDTO[] = [];
  ulogovan = false;
  korisnikId: number | null = null;

  success = '';
  error = '';

  private takmicenjeService = inject(TakmicenjeService);
  private prijavaService = inject(PrijavaService);
  private authService = inject(AuthService);
  private route = inject(ActivatedRoute);

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.ucitajNajavljeno(id);

    const user = this.authService.getUser();
    if (user) {
      this.korisnikId = user.id;
      this.ulogovan = true;
    }
  }

  ucitajNajavljeno(id: number): void {
    this.takmicenjeService.getNajavljeno(id).subscribe({
      next: (data) => {
        this.takmicenje = data.takmicenje;
        this.prijave = data.prijave;
      },
      error: (err) => {
        console.error('Greška prilikom učitavanja takmičenja:', err);
        this.error = 'Greška prilikom učitavanja takmičenja.';
      },
    });
  }

  prijaviSe(): void {
    if (!this.korisnikId || !this.takmicenje) return;

    this.success = '';
    this.error = '';

    this.prijavaService.prijaviSe(this.takmicenje.id!, this.korisnikId).subscribe({
      next: (res) => {
        this.success = res.success;
        this.ucitajNajavljeno(this.takmicenje!.id!); // osveži listu
      },
      error: (err) => {
        this.error = err.error?.error || 'Greška pri prijavi.';
      }
    });
  }

  odjaviSe(): void {
    if (!this.korisnikId || !this.takmicenje) return;

    this.success = '';
    this.error = '';

    this.prijavaService.odjaviSe(this.takmicenje.id!, this.korisnikId).subscribe({
      next: (res) => {
        this.success = res.success;
        this.ucitajNajavljeno(this.takmicenje!.id!);
      },
      error: (err) => {
        this.error = err.error?.error || 'Greška pri odjavi.';
      }
    });
  }

  // 🔹 Pomoćna metoda: proverava da li je trenutni korisnik već prijavljen
  jePrijavljen(): boolean {
    if (!this.korisnikId) return false;
    return this.prijave.some(p => p.korisnik.id === this.korisnikId);
  }
}
