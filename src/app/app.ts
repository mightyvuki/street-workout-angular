import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from "./fragments/header/header";
import { AuthService } from './services/auth';
import { AdminHeader } from './fragments/admin-header/admin-header';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, AdminHeader],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  constructor(public authService: AuthService) {}
  uloga() {
    return this.authService.user()?.uloga;
  }
}
