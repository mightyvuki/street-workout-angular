import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login';
import { Home } from './home/home/home';
import { AdminHome } from './home/admin-home/admin-home';
import { SledecaTakmicenjaComponent } from './user/sledeca-takmicenja/sledeca-takmicenja';
import { RezultatiComponent } from './user/rezultati/rezultati';
import { RegisterComponent } from './auth/register/register';
import { OdrzanoComponent } from './takmicenje/odrzano/odrzano';
import { NajavljenoComponent } from './takmicenje/najavljeno/najavljeno';
import { TakmicenjaAdminComponent } from './admin/takmicenja-admin/takmicenja-admin';
import { NovoTakmicenjeComponent } from './admin/novo-takmicenje/novo-takmicenje';
import { IzmenaTakmicenjaComponent } from './admin/izmena-takmicenja/izmena-takmicenja';

import { AuthGuard } from './guards/auth-guard';
import { AdminGuard } from './guards/admin-guard';
import { ParentComponent } from './demo/parent/parent';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: Home },
  { path: 'user/upcoming', component: SledecaTakmicenjaComponent, canActivate: [AuthGuard] },
  { path: 'user/results', component: RezultatiComponent, canActivate: [AuthGuard] },
  { path: 'takmicenje/odrzano/:id', component: OdrzanoComponent },
  { path: 'takmicenje/najavljeno/:id', component: NajavljenoComponent },
  { path: 'adminHome', component: AdminHome, canActivate: [AdminGuard] },
  { path: 'admin/takmicenja', component: TakmicenjaAdminComponent, canActivate: [AdminGuard] },
  { path: 'admin/takmicenja/novo', component: NovoTakmicenjeComponent, canActivate: [AdminGuard] },
  { path: 'admin/takmicenja/izmeni/:id', component: IzmenaTakmicenjaComponent, canActivate: [AdminGuard] },
  { path: 'admin/prijave', component: ParentComponent, canActivate: [AdminGuard] },
  { path: '**', redirectTo: '/home' }
];
