import { Component, inject, Signal } from '@angular/core';
import { FreyLoaderService } from 'freya/loader';

@Component({
  selector: 'app-loader',
  imports: [],
  templateUrl: './loader.component.html',
  styleUrl: './loader.component.scss',
})
export class LoaderComponent {
  statusLoader$$: Signal<boolean>;
  private readonly loader = inject(FreyLoaderService);

  constructor() {
    this.statusLoader$$ = this.loader.statusLoader;
  }
}
