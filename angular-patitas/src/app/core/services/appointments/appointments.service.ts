import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppointmentStatusEnum } from 'src/app/utils/enums';
import { environment } from 'src/environments/environment';
import { Appointment } from './appointments.model';

@Injectable({
  providedIn: 'root',
})
export class AppointmentsService {
  private readonly http = inject(HttpClient);

  getAppointments(): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(environment.api.concat('appointments'));
  }

  postAppointment(body: Appointment): Observable<void> {
    return this.http.post<void>(environment.api.concat('appointments'), body);
  }

  updateAppointmentStatus(id: number, status: AppointmentStatusEnum): Observable<void> {
    return this.http.patch<void>(
      environment.api.concat(`appointments/${id}/status?status=${status}`),
      null
    );
  }

  getAppointmentById(appointmentId: number): Observable<Appointment> {
    return this.http.get<Appointment>(
      environment.api.concat(`appointments/${appointmentId}`)
    );
  }
}
