import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ButtonBackComponent, LoaderComponent } from './shared/components';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, LoaderComponent, ButtonBackComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {}
