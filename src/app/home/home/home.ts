import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { TakmicenjeService } from '../../services/takmicenje';
import { TakmicenjeDTO } from '../../models/TakmicenjeDTO';
import { AuthService } from '../../services/auth';
import { HeaderComponent } from '../../fragments/header/header';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

  najavljenaTakmicenja: TakmicenjeDTO[] = [];
  odrzanaTakmicenja: TakmicenjeDTO[] = [];

  constructor(private takmicenjeService: TakmicenjeService) { }


  ngOnInit(): void {
    this.takmicenjeService.getNajavljena().subscribe(data => this.najavljenaTakmicenja = data);
    this.takmicenjeService.getOdrzana().subscribe(data => this.odrzanaTakmicenja = data);
  }
}
