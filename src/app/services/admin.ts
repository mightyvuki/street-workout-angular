import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private apiUrl = 'http://localhost:8080/LSW/api/admin';

  constructor(private http: HttpClient) {}

  // TAKMIČENJA
  getTakmicenja(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/takmicenja`);
  }

  getTakmicenje(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/takmicenja/${id}`);
  }

  addTakmicenje(takmicenje: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/takmicenja`, takmicenje);
  }

  updateTakmicenje(id: number, takmicenje: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/takmicenja/${id}`, takmicenje);
  }

  deleteTakmicenje(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/takmicenja/${id}`);
  }

  // PRIJAVE
  getPrijave(takmicenjeId?: number): Observable<any[]> {
    const url = takmicenjeId ? `${this.apiUrl}/prijave?takmicenjeId=${takmicenjeId}` : `${this.apiUrl}/prijave`;
    return this.http.get<any[]>(url);
  }

  addPrijava(korisnikId: number, takmicenjeId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/prijave?korisnikId=${korisnikId}&takmicenjeId=${takmicenjeId}`, {});
  }

  deletePrijava(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/prijave/${id}`);
  }

  // IZVEŠTAJI
  downloadPrijavljeniTakmicariPDF(id: number): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/izvestaji/prijavljeniTakmicari/${id}`, { responseType: 'blob' });
  }

  downloadProsekDisciplinaPDF(id: number): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/izvestaji/prosekPoDisciplinama/${id}`, { responseType: 'blob' });
  }
}
