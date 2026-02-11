import { Component, inject, OnInit, output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FreyDatepickerModule } from 'freya/datepicker';
import { FreyFormModule } from 'freya/form';
import { FreyTimepickerComponent } from 'freya/timepicker';

@Component({
  selector: 'app-appointment-form',
  imports: [
    ReactiveFormsModule,
    FreyFormModule,
    FreyTimepickerComponent,
    FreyDatepickerModule,
  ],
  templateUrl: './appointment-form.html',
  styleUrl: './appointment-form.scss',
})
export class AppointmentForm implements OnInit {
  formGroup: FormGroup;
  formGroupOutput = output<FormGroup>();

  private readonly formBuilder = inject(FormBuilder);

  constructor() {
    this.buildForm();
  }

  ngOnInit(): void {
    this.formGroupOutput.emit(this.formGroup);
  }

  private buildForm(): void {
    this.formGroup = this.formBuilder.group({
      clientName: ['', [Validators.required]],
      petName: ['', [Validators.required]],
      description: ['', [Validators.required]],
      date: ['', [Validators.required]],
      time: ['09:00:00', [Validators.required]],
    });
  }
}
