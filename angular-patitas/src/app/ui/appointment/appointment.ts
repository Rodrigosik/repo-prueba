import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FreyButtonDirective } from 'freya/button';
import { FreyFormModule } from 'freya/form';
import { FreyTimepickerComponent } from 'freya/timepicker';

@Component({
  selector: 'app-appointment',
  imports: [
    ReactiveFormsModule,
    FreyFormModule,
    FreyButtonDirective,
    FreyTimepickerComponent,
  ],
  templateUrl: './appointment.html',
  styleUrl: './appointment.scss',
})
export class Appointment {
  formGroup: FormGroup;

  private readonly formBuilder = inject(FormBuilder);

  constructor() {
    this.buildForm();
  }

  onSubmit(): void {
    if (this.formGroup.invalid) {
      this.formGroup.markAllAsTouched();
      return;
    }

    console.log('Form submitted:', this.formGroup.value);
  }
  private buildForm(): void {
    this.formGroup = this.formBuilder.group({
      clientName: ['', [Validators.required]],
      petName: ['', [Validators.required]],
      description: ['', [Validators.required]],
      date: ['', [Validators.required]],
    });
  }
}
