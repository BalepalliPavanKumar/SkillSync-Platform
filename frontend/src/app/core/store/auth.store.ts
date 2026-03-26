import { computed, inject } from '@angular/core';
import { patchState, signalStore, withComputed, withMethods, withState } from '@ngrx/signals';
import { AuthState, LoginRequest } from '../models/auth.model';
import { AuthService } from '../services/auth.service';
import { firstValueFrom } from 'rxjs';

const initialState: AuthState = {
  user: null,
  token: null,
  isLoading: false,
  error: null,
};

export const AuthStore = signalStore(
  { providedIn: 'root' },
  withState(initialState),
  withComputed(({ user }) => ({
    isAuthenticated: computed(() => !!user()),
    isAdmin: computed(() => user()?.roles.includes('ROLE_ADMIN') ?? false),
    isMentor: computed(() => user()?.roles.includes('ROLE_MENTOR') ?? false),
    isLearner: computed(() => user()?.roles.includes('ROLE_LEARNER') ?? false),
  })),
  withMethods((store, authService = inject(AuthService)) => ({
    async login(credentials: LoginRequest) {
      patchState(store, { isLoading: true, error: null });
      try {
        const response = await firstValueFrom(authService.login(credentials));
        patchState(store, { 
          user: response.user, 
          token: response.token, 
          isLoading: false 
        });
      } catch (err: any) {
        patchState(store, { 
          isLoading: false, 
          error: err.error?.message || 'Login failed' 
        });
      }
    },
    logout() {
      patchState(store, initialState);
    },
  }))
);
