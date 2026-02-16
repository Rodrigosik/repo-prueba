import { Location } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { FreyButtonDirective } from 'freya';

@Component({
  selector: 'app-button-back',
  imports: [FreyButtonDirective],
  templateUrl: './button-back.html',
  styleUrl: './button-back.scss',
})
export class ButtonBackComponent {
  private router = inject(Router);
  private location = inject(Location);

  get isNotHome(): boolean {
    return this.router.url !== '/home';
  }

  returnBack(): void {
    this.location.back();
  }
}
