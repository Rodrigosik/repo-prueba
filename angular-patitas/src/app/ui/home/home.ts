import { Component } from '@angular/core';
import { FreyButtonDirective } from 'freya';

@Component({
  selector: 'app-home',
  imports: [FreyButtonDirective],
  templateUrl: './home.html',
  styleUrls: ['./home.scss'],
})
export class Home {}
