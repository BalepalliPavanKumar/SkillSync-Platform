import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatStepperModule } from '@angular/material/stepper';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-sessions',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatStepperModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatListModule,
    MatIconModule,
    MatSelectModule,
    MatCardModule
  ],
  templateUrl: './sessions.component.html',
  styleUrls: ['./sessions.component.scss']
})
export class SessionsComponent {
  private _formBuilder = inject(FormBuilder);

  firstFormGroup = this._formBuilder.group({
    mentor: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    date: ['', Validators.required],
    time: ['', Validators.required],
  });
  thirdFormGroup = this._formBuilder.group({
    topic: ['', Validators.required],
    description: ['']
  });

  mentors = [
    { id: 1, name: 'Priya Sharma', role: 'Sr. Backend Engineer' },
    { id: 2, name: 'Arjun Mehta', role: 'ML Engineer' },
    { id: 3, name: 'Neha Kapoor', role: 'Frontend Architect' }
  ];

  timeSlots = ['09:00 AM', '10:00 AM', '11:00 AM', '02:00 PM', '03:00 PM', '04:00 PM'];

  isLinear = true;
  bookingConfirmed = signal(false);

  confirmBooking() {
    this.bookingConfirmed.set(true);
  }
}
