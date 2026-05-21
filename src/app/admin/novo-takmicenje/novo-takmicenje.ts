import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin';
import { AuthService } from '../../services/auth';
import { TakmicenjeDTO } from '../../models/TakmicenjeDTO';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-novo-takmicenje',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './novo-takmicenje.html',
  styleUrls: ['./novo-takmicenje.css']
})
export class NovoTakmicenjeComponent implements OnInit {

  forma!: FormGroup;
  error = '';
  success = '';
  submitting = false;

  constructor(
    private fb: FormBuilder,
    private adminService: AdminService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.forma = this.fb.group({
      naziv: ['', [Validators.required, Validators.maxLength(100)]],
      datum: ['', [Validators.required, this.datumBuducnostValidator]],
      lokacija: ['', [Validators.required, Validators.maxLength(100)]],
      opis: ['', [Validators.required, Validators.maxLength(500)]],
    });
  }

  datumBuducnostValidator(control: any) {
    const unos = new Date(control.value);
    const danas = new Date();
    danas.setHours(0,0,0,0);
    return unos >= danas ? null : { datumUGresci: true };
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

    const admin = this.authService.getUser();
    if (!admin) {
      this.error = 'Niste ulogovani kao admin.';
      this.submitting = false;
      return;
    }

    const takmicenje: TakmicenjeDTO = {
      naziv: this.f['naziv'].value,
      datum: this.f['datum'].value,
      lokacija: this.f['lokacija'].value,
      opis: this.f['opis'].value,
      organizator: admin
    };

    this.adminService.addTakmicenje(takmicenje).subscribe({
      next: () => {
        this.success = 'Takmičenje je uspešno dodato!';
        setTimeout(() => this.router.navigate(['/admin/takmicenja']), 1000);
      },
      error: (err) => {
        console.error(err);
        this.error = err.error?.error || 'Greška pri dodavanju takmičenja.';
        this.submitting = false;
      }
    });
  }
}
