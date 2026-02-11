import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FreyButtonDirective } from 'freya';

@Component({
  selector: 'app-home',
  imports: [FreyButtonDirective, RouterLink],
  templateUrl: './home.html',
  styleUrls: ['./home.scss'],
})
export class Home {}
