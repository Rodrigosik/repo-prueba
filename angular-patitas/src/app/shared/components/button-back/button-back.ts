import { Component } from '@angular/core';
import { FreyButtonDirective } from 'freya';

@Component({
  selector: 'app-button-back',
  imports: [FreyButtonDirective],
  templateUrl: './button-back.html',
  styleUrl: './button-back.scss',
})
export class ButtonBackComponent {
  returnBack(): void {
    window.history.back();
  }
}
