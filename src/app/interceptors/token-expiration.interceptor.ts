import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { AuthService } from '../modules/auth/pages/service/auth.service';

import { Router } from '@angular/router';

@Injectable()
export class TokenExpirationInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService,
              private router: Router) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const isTokenExpired = this.authService.checkTokenExpiration();

    if (isTokenExpired) {
      localStorage.clear();
      this.router.navigate([''])
    }

    return next.handle(request).pipe(
      tap(
        (event: HttpEvent<any>) => {},
        (error: any) => {
          if (error.status === 401) {
            localStorage.clear();
            this.router.navigate([''])
          }
        }
      )
    );
  }
}
