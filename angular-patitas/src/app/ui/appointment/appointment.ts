import { Component, inject } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { FreyButtonDirective } from 'freya';
import { FreyDatepickerModule } from 'freya/datepicker';
import { FreyFormModule } from 'freya/form';
import { AlertService, Appointment, AppointmentsService } from 'src/app/core/services';
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
export class AppointmentComponent {
  formGroup: FormGroup;

  // ===================================
  // 📦 Zona de Inyección de Servicios
  // ===================================
  private readonly alertService = inject(AlertService);
  private readonly appointmentsService = inject(AppointmentsService);

  onSubmit(): void {
    if (this.formGroup.invalid) {
      this.formGroup.markAllAsTouched();
      return;
    }

    const body = new Appointment().cleaner({
      ...this.formGroup.value,
    });

    this.postAppointment(body);
  }

  private postAppointment(body: Appointment): void {
    this.appointmentsService.postAppointment(body).subscribe({
      next: response => {
        this.alertService.successAlert('Cita creada exitosamente');
        this.formGroup.reset();
      },
    });
  }
}
