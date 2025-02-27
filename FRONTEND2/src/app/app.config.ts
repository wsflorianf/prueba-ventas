import { ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';

import { AppRoutingModule, routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), importProvidersFrom(AppRoutingModule), provideHttpClient()]
};
