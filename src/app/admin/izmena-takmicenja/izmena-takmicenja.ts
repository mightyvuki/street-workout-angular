import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule, NgClass } from '@angular/common';
import { TakmicenjeDTO } from '../../models/TakmicenjeDTO';
import { AdminService } from '../../services/admin';

@Component({
  selector: 'app-izmena-takmicenja',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, NgClass],
  templateUrl: './izmena-takmicenja.html',
  styleUrls: ['./izmena-takmicenja.css']
})
export class IzmenaTakmicenjaComponent implements OnInit {

  forma!: FormGroup;
  takmicenjeId!: number;
  error = '';
  success = '';
  submitting = false;
  ucitavanje = true;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {
    this.forma = this.fb.group({
      naziv: ['', [Validators.required, Validators.maxLength(100)]],
      datum: ['', [Validators.required]],
      lokacija: ['', [Validators.required, Validators.maxLength(100)]],
      opis: ['', [Validators.required, Validators.maxLength(500)]],
    });

    this.takmicenjeId = Number(this.route.snapshot.paramMap.get('id'));
    if (isNaN(this.takmicenjeId)) {
      this.error = 'Neispravan ID takmičenja.';
      this.ucitavanje = false;
      return;
    }

    this.adminService.getTakmicenje(this.takmicenjeId).subscribe({
      next: (data: TakmicenjeDTO) => {
        this.forma.patchValue({
          naziv: data.naziv,
          datum: data.datum ? data.datum.substring(0, 10) : '',
          lokacija: data.lokacija,
          opis: data.opis
        });
        this.ucitavanje = false;
      },
      error: () => {
        this.error = 'Greška pri učitavanju takmičenja.';
        this.ucitavanje = false;
      }
    });
  }

  get f() { return this.forma.controls; }

  submit(): void {
    if (this.forma.invalid) {
      this.forma.markAllAsTouched();
      return;
    }

    this.submitting = true;
    this.error = '';
    this.success = '';

    const izmenjenoTakmicenje: TakmicenjeDTO = {
      id: this.takmicenjeId,
      naziv: this.f['naziv'].value,
      datum: this.f['datum'].value,
      lokacija: this.f['lokacija'].value,
      opis: this.f['opis'].value
    };

    this.adminService.updateTakmicenje(this.takmicenjeId, izmenjenoTakmicenje).subscribe({
      next: () => {
        this.success = 'Takmičenje je uspešno izmenjeno!';
        setTimeout(() => this.router.navigate(['/admin/takmicenja']), 1500);
      },
      error: (err) => {
        console.error(err);
        this.error = err.error?.error || 'Greška pri izmeni takmičenja.';
        this.submitting = false;
      }
    });
  }
}
