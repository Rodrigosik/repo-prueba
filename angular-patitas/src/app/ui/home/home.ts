import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FreyButtonDirective } from 'freya';

@Component({
  selector: 'app-home',
  imports: [FreyButtonDirective, RouterModule],
  templateUrl: './home.html',
  styleUrls: ['./home.scss'],
})
export class Home {}
