import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'home',
    loadComponent: () => import('./ui/home/home').then(m => m.Home),
  },
  {
    path: 'agendar',
    loadComponent: () =>
      import('./ui/appointment/appointment').then(m => m.AppointmentComponent),
  },
  {
    path: 'lista',
    loadComponent: () => import('./ui/list/list').then(m => m.List),
  },
  {
    path: '**',
    pathMatch: 'full',
    redirectTo: 'home',
  },
];
