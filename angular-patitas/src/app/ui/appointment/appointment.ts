import { Component } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { FreyButtonDirective } from 'freya';
import { FreyDatepickerModule } from 'freya/datepicker';
import { FreyFormModule } from 'freya/form';
import { AppointmentForm } from 'src/app/shared/components';

@Component({
  selector: 'app-appointment',
  imports: [
    ReactiveFormsModule,
    FreyFormModule,
    FreyDatepickerModule,
    AppointmentForm,
    RouterLink,
    FreyButtonDirective,
  ],
  templateUrl: './appointment.html',
  styleUrl: './appointment.scss',
})
export class Appointment {
  formGroup: FormGroup;

  onSubmit(): void {
    if (this.formGroup.invalid) {
      this.formGroup.markAllAsTouched();
      return;
    }

    console.log('Form submitted:', this.formGroup.value);
  }
}
