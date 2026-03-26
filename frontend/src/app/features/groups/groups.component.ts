import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatProgressBarModule } from '@angular/material/progress-bar';

@Component({
  selector: 'app-groups',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatChipsModule,
    MatProgressBarModule
  ],
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.scss']
})
export class GroupsComponent {
  groups = signal([
    {
      name: 'Spring Boot Enthusiasts',
      description: 'A community for sharing best practices, microservices architecture, and troubleshooting.',
      members: 124,
      activeSessions: 2,
      tags: ['Backend', 'Java', 'Microservices'],
      image: 'SB',
      joined: true
    },
    {
      name: 'Frontend Wizards',
      description: 'Discussing the latest in Angular, React, and modern CSS techniques.',
      members: 89,
      activeSessions: 1,
      tags: ['Frontend', 'Angular', 'UX'],
      image: 'FW',
      joined: false
    },
    {
      name: 'Cloud Native India',
      description: 'Focused on AWS, Azure, and Kubernetes deployments for scale.',
      members: 156,
      activeSessions: 0,
      tags: ['Cloud', 'DevOps', 'K8s'],
      image: 'CN',
      joined: false
    },
    {
      name: 'DSA Mastery',
      description: 'Daily challenges and peer reviews for competitive programming.',
      members: 240,
      activeSessions: 5,
      tags: ['DSA', 'Algorithms', 'Interview'],
      image: 'DS',
      joined: true
    }
  ]);

  toggleJoin(group: any) {
    group.joined = !group.joined;
  }
}
