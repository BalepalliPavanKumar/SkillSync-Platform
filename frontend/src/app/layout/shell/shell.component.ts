import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthStore } from '../../core/store/auth.store';

@Component({
  selector: 'app-shell',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatButtonModule,
    MatToolbarModule
  ],
  templateUrl: './shell.component.html',
  styleUrls: ['./shell.component.scss']
})
export class ShellComponent {
  readonly authStore = inject(AuthStore);

  menuItems = [
    { label: 'Dashboard', icon: 'dashboard', route: '/dashboard' },
    { label: 'Find Mentors', icon: 'person_search', route: '/mentors' },
    { label: 'My Sessions', icon: 'event', route: '/sessions' },
    { label: 'Learning Groups', icon: 'groups', route: '/groups' },
    { label: 'Reviews', icon: 'star', route: '/reviews' },
    { label: 'My Profile', icon: 'account_circle', route: '/profile' }
  ];

  logout() {
    this.authStore.logout();
  }
}
