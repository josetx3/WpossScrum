import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree,Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../modules/auth/pages/service/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  canActivate(): boolean{
    if (this.authService.isAuthenticated()) {

      return true;
    } else {
      this.router.navigate(['/auth']);
      return false;
    }
  }

  constructor(private authService : AuthService, private router : Router ){}



}
