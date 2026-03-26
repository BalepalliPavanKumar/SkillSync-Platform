import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatChipsModule } from '@angular/material/chips';
import { AuthStore } from '../../core/store/auth.store';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatChipsModule
  ],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  readonly authStore = inject(AuthStore);

  // Mock data for the dashboard (In a real app, these would be fetched from services)
  stats = signal([
    { label: 'Upcoming Sessions', value: 2, icon: 'event', color: '#e71e30', trend: 'This week' },
    { label: 'Connected Mentors', value: 4, icon: 'person_add', color: '#3b82f6', trend: '+1 this month' },
    { label: 'Active Groups', value: 3, icon: 'groups', color: '#10b981', trend: '2 new posts' },
    { label: 'Sessions Completed', value: 12, icon: 'star', color: '#f59e0b', trend: '+3 this month' }
  ]);

  recommendedMentors = signal([
    { name: 'Priya Sharma', role: 'Sr. Backend Engineer', rating: 4.8, reviews: 42, rate: 800, skills: ['Spring Boot', 'Java', 'REST APIs'], image: 'PS' },
    { name: 'Arjun Mehta', role: 'Machine Learning Expert', rating: 4.7, reviews: 28, rate: 600, skills: ['Python', 'TensorFlow', 'ML'], image: 'AM' },
    { name: 'Neha Kapoor', role: 'Frontend Architect', rating: 4.9, reviews: 35, rate: 750, skills: ['Angular', 'RxJS', 'NgRx'], image: 'NK' }
  ]);

  upcomingSessions = signal([
    { date: 'Mar 25', time: '10:00 AM', mentor: 'Priya Sharma', topic: 'Spring Boot REST APIs', status: 'Accepted' },
    { date: 'Mar 27', time: '03:00 PM', mentor: 'Arjun Mehta', topic: 'ML Fundamentals', status: 'Pending' }
  ]);
}
