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
import { Observable } from 'rxjs';
import { AlertService, AppointmentsService } from 'src/app/core/services';
import { AppointmentForm, TableManagerComponent } from 'src/app/shared/components';
import { AppointmentModalData } from 'src/app/shared/components/appointment-form/appointment-form';

interface Appointment {
  clientName: string;
  petName: string;
  description: string;
  date: string; // ISO date string, e.g. "2026-02-11T10:00:00"
  time: string; // ISO date string, e.g. "2026-02-11T10:00:00"
  status: boolean;
}

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
  ],
  templateUrl: './list.html',
  styleUrl: './list.scss',
})
export class List implements OnInit {
  dataSource$$ = signal<Appointment[]>([
    {
      clientName: 'Albaro',
      petName: 'Firulais',
      description:
        'Vacunación anual kjasdhjkhksadh hjsajdhjkashdjkash jhasjkdhkjashdkjhsajk khajskjdhkjashd sdkljasdjaksjdklajsl kljasdkljaksldjklasjdlk',
      date: '2026-02-11T10:00:00',
      time: '2026-02-11T10:00:00',
      status: true,
    },
    {
      clientName: 'María',
      petName: 'Michi',
      description: 'Consulta general',
      date: '2026-02-12T12:30:00',
      time: '2026-02-12T12:30:00',
      status: false,
    },
    {
      clientName: 'Carlos',
      petName: 'Rocky',
      description: 'Desparasitación',
      date: '2026-02-13T09:00:00',
      time: '2026-02-13T09:00:00',
      status: true,
    },
    {
      clientName: 'Lucía',
      petName: 'Luna',
      description: 'Chequeo dental',
      date: '2026-02-14T15:00:00',
      time: '2026-02-14T15:00:00',
      status: true,
    },
    {
      clientName: 'Pedro',
      petName: 'Max',
      description: 'Corte de uñas',
      date: '2026-02-15T11:00:00',
      time: '2026-02-15T11:00:00',
      status: false,
    },
    {
      clientName: 'Ana',
      petName: 'Nina',
      description: 'Vacuna antirrábica',
      date: '2026-02-16T13:30:00',
      time: '2026-02-16T13:30:00',
      status: true,
    },
    {
      clientName: 'Jorge',
      petName: 'Toby',
      description: 'Consulta dermatológica',
      date: '2026-02-17T16:00:00',
      time: '2026-02-17T16:00:00',
      status: false,
    },
    {
      clientName: 'Sofía',
      petName: 'Pelusa',
      description: 'Control de peso',
      date: '2026-02-18T10:30:00',
      time: '2026-02-18T10:30:00',
      status: true,
    },
    {
      clientName: 'Miguel',
      petName: 'Simba',
      description: 'Revisión de oídos',
      date: '2026-02-19T14:00:00',
      time: '2026-02-19T14:00:00',
      status: true,
    },
    {
      clientName: 'Elena',
      petName: 'Coco',
      description: 'Consulta de comportamiento',
      date: '2026-02-20T17:00:00',
      time: '2026-02-20T17:00:00',
      status: false,
    },
  ]);

  // ===================================
  // 📦 Zona de Inyección de Servicios
  // ===================================
  private readonly alertService = inject(AlertService);
  private readonly modalService = inject(FreyModalService);
  private readonly appointmentsService = inject(AppointmentsService);

  ngOnInit(): void {
    this.getAppointments();
  }

  onDelete(row: Appointment): void {
    console.log('Eliminar cita:', row);
    const title = `¿Eliminar la cita de ${row.clientName}?`;
    this.alertService
      .warningAlert(title, 'Esta acción no se puede deshacer.')
      .subscribe((result: boolean) => {
        if (result) {
          // this.deleteEvento(row.id);
        }
      });
  }

  onModalForm(data): Observable<any> {
    const config = new FreyModalConfigModel();
    config.customWidth.large = 30;
    config.dataSource = new AppointmentModalData();
    config.dataSource.isReadOnly.set(true);
    return this.modalService.openModal(AppointmentForm, config) as Observable<any>;
  }

  private getAppointments(): void {
    this.appointmentsService.getAppointments().subscribe({
      next: response => {
        // this.dataSource$$.set(response);
        console.log(response);
      },
    });
  }

  // private deleteEvento(id: number): void {
  //   this.eventosService.deleteEvento(id).subscribe({
  //     next: () => {
  //       this.getEventos();
  //     },
  //   });
  // }
}
