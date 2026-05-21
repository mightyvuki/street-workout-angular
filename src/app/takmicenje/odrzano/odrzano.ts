import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule, KeyValuePipe, DatePipe } from '@angular/common';
import { TakmicenjeService } from '../../services/takmicenje';
import { TakmicenjeDTO } from '../../models/TakmicenjeDTO';
import { RezultatDTO } from '../../models/RezultatDTO';

@Component({
  selector: 'app-odrzano',
  standalone: true,
  imports: [CommonModule, KeyValuePipe, DatePipe],
  templateUrl: './odrzano.html',
  styleUrls: ['./odrzano.css']
})
export class OdrzanoComponent implements OnInit {

  takmicenje?: TakmicenjeDTO;
  rezultati: Record<string, RezultatDTO[]> = {};
  odrzano = false;

  loading = true;
  error = '';
  success = '';

  constructor(
    private takmicenjeService: TakmicenjeService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    if (!id) {
      this.error = 'Nije prosleđen ID takmičenja.';
      this.loading = false;
      return;
    }

    this.takmicenjeService.getOdrzano(id).subscribe({
      next: (data) => {
        this.takmicenje = data.takmicenje;
        this.rezultati = data.rezultati;
        this.odrzano = data.odrzano;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.error = 'Greška pri učitavanju podataka o takmičenju.';
        this.loading = false;
      }
    });
  }
}