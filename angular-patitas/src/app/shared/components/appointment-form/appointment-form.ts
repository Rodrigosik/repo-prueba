import { Component, computed, inject, OnInit, output, signal } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FreyDatepickerModule } from 'freya/datepicker';
import { FreyFormModule } from 'freya/form';
import { FreyInputValidationDirective } from 'freya/input-validation';
import { FreyTimepickerComponent } from 'freya/timepicker';
import { Appointment } from 'src/app/core/services';
import { parseFechaYYYYMMDDToDate } from 'src/app/utils/helpers';

export class AppointmentModalData {
  isReadOnly = signal(false);
  formValue: Appointment;
}

@Component({
  selector: 'app-appointment-form',
  imports: [
    ReactiveFormsModule,
    FreyFormModule,
    FreyTimepickerComponent,
    FreyDatepickerModule,
    FreyInputValidationDirective,
  ],
  templateUrl: './appointment-form.html',
  styleUrl: './appointment-form.scss',
})
export class AppointmentForm implements OnInit {
  dataSource: AppointmentModalData = null;

  formGroup: FormGroup;
  isReadOnly = signal<boolean>(false);
  formGroupOutput = output<FormGroup>();

  _isReadOnly = computed(() => this.dataSource?.isReadOnly() || this.isReadOnly());

  private readonly formBuilder = inject(FormBuilder);

  constructor() {
    this.buildForm();
  }

  ngOnInit(): void {
    this.formGroupOutput.emit(this.formGroup);

    if (this.dataSource && this.dataSource.formValue) {
      this.formGroup.patchValue({
        ...this.dataSource.formValue,
        date: parseFechaYYYYMMDDToDate(this.dataSource.formValue.date),
      });
    }
  }

  private buildForm(): void {
    this.formGroup = this.formBuilder.group({
      clientName: ['', [Validators.required]],
      petName: ['', [Validators.required]],
      reason: ['', [Validators.required]],
      date: ['', [Validators.required]],
      time: ['09:00:00', [Validators.required]],
    });
  }
}
