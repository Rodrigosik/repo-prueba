import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'home',
    loadComponent: () => import('./ui/home/home').then(m => m.Home),
  },
  {
    path: 'agendar',
    loadComponent: () => import('./ui/appointment/appointment').then(m => m.Appointment),
  },
  {
    path: '**',
    pathMatch: 'full',
    redirectTo: 'home',
  },
];
