import { DatePipe, NgClass, SlicePipe } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { FreyButtonDirective } from 'freya/button';
import { FreyModalConfigModel, FreyModalService } from 'freya/modal';
import {
  FreyCellDefDirective,
  FreyColumnDefDirective,
  FreyColumnSortDirective,
  FreyHeaderCellDefDirective,
  FreyTableComponent,
} from 'freya/table';
import { FreyTooltipDirective } from 'freya/tooltip';
import { Observable } from 'rxjs';
import { AlertService, Appointment, AppointmentsService } from 'src/app/core/services';
import {
  AppointmentForm,
  ButtonBackComponent,
  TableManagerComponent,
} from 'src/app/shared/components';
import { AppointmentModalData } from 'src/app/shared/components/appointment-form/appointment-form';

@Component({
  selector: 'app-list',
  imports: [
    FreyColumnDefDirective,
    FreyHeaderCellDefDirective,
    FreyCellDefDirective,
    FreyColumnSortDirective,
    TableManagerComponent,
    FreyTableComponent,
    FreyButtonDirective,
    NgClass,
    DatePipe,
    SlicePipe,
    ButtonBackComponent,
    FreyTooltipDirective,
  ],
  templateUrl: './list.html',
  styleUrl: './list.scss',
})
export class ListComponent implements OnInit {
  dataSource$$ = signal<Appointment[]>([]);

  // ===================================
  // 📦 Zona de Inyección de Servicios
  // ===================================
  private readonly alertService = inject(AlertService);
  private readonly modalService = inject(FreyModalService);
  private readonly appointmentsService = inject(AppointmentsService);

  ngOnInit(): void {
    this.getAppointments();
  }

  onClose(row: Appointment): void {
    const title = `¿Terminar la cita de ${row.clientName}?`;
    this.alertService
      .warningAlert(title, 'Esta acción no se puede deshacer.')
      .subscribe((result: boolean) => {
        if (result) {
          this.closeAppointment(row.id);
        }
      });
  }

  onModalForm(row: Appointment): Observable<any> {
    const config = new FreyModalConfigModel();
    config.customWidth.large = 30;
    config.dataSource = new AppointmentModalData();
    config.dataSource.isReadOnly.set(true);
    config.dataSource.formValue = row;
    return this.modalService.openModal(AppointmentForm, config) as Observable<any>;
  }

  private getAppointments(): void {
    this.appointmentsService.getAppointments().subscribe({
      next: response => {
        this.dataSource$$.set(response);
      },
    });
  }

  private closeAppointment(id: number): void {
    this.appointmentsService.updateAppointmentStatus(id, 'COMPLETED').subscribe({
      next: () => {
        this.getAppointments();
        this.alertService.successAlert(
          'Cita terminada',
          'La cita ha sido marcada como completada.'
        );
      },
    });
  }
}
