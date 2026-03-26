import { Component, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatChipsModule } from '@angular/material/chips';
import { MatSelectModule } from '@angular/material/select';
import { MatBadgeModule } from '@angular/material/badge';

@Component({
  selector: 'app-mentors',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatChipsModule,
    MatSelectModule,
    MatBadgeModule
  ],
  templateUrl: './mentors.component.html',
  styleUrls: ['./mentors.component.scss']
})
export class MentorsComponent {
  searchQuery = signal('');
  selectedSkill = signal('All');
  
  allMentors = signal([
    { name: 'Priya Sharma', role: 'Sr. Backend Engineer', rating: 4.8, reviews: 42, rate: 800, skills: ['Spring Boot', 'Java', 'JPA'], image: 'PS', available: true },
    { name: 'Arjun Mehta', role: 'ML Engineer', rating: 4.7, reviews: 28, rate: 600, skills: ['Python', 'TensorFlow', 'ML'], image: 'AM', available: true },
    { name: 'Neha Kapoor', role: 'Frontend Architect', rating: 4.9, reviews: 35, rate: 750, skills: ['React', 'Node.js', 'TypeScript'], image: 'NK', available: true },
    { name: 'Rahul Gupta', role: 'Full Stack Dev', rating: 4.6, reviews: 19, rate: 500, skills: ['DSA', 'Java', 'LeetCode'], image: 'RG', available: true },
    { name: 'Sanjay Kumar', role: 'Cloud Architect', rating: 4.95, reviews: 87, rate: 950, skills: ['Microservices', 'Docker'], image: 'SK', available: true },
    { name: 'Divya Verma', role: 'UI Engineer', rating: 4.7, reviews: 31, rate: 700, skills: ['Angular', 'RxJS', 'NgRx'], image: 'DV', available: true }
  ]);

  skills = ['All', 'Java', 'Spring Boot', 'Angular', 'Python', 'React', 'Docker'];

  filteredMentors = computed(() => {
    const query = this.searchQuery().toLowerCase();
    const skill = this.selectedSkill();
    
    return this.allMentors().filter(m => {
      const matchesSearch = m.name.toLowerCase().includes(query) || m.role.toLowerCase().includes(query);
      const matchesSkill = skill === 'All' || m.skills.some(s => s.toLowerCase().includes(skill.toLowerCase()));
      return matchesSearch && matchesSkill;
    });
  });

  onSearch(event: Event) {
    const input = event.target as HTMLInputElement;
    this.searchQuery.set(input.value);
  }

  selectSkill(skill: string) {
    this.selectedSkill.set(skill);
  }
}
