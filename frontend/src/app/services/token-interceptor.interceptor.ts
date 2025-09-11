import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { SnackbarService } from './snackbar.service';

@Injectable()
export class TokenInterceptorInterceptor implements HttpInterceptor {

  constructor(private router: Router, private snackbarService: SnackbarService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = localStorage.getItem('token');
    if (token) {
      request = request.clone({
        setHeaders: {Authorization: `Bearer ${token}`}
      });
    }
    return next.handle(request).pipe(
      catchError((err: HttpErrorResponse) => {
        if (err instanceof HttpErrorResponse) {
          if ((err.status === 401 || err.status === 403) && err.url?.includes('/checkToken')) {
            console.log(err);
            const token = localStorage.getItem('token');
            if (token) {
              this.snackbarService.openSnackBar("You must login first", "error");
            }
            localStorage.clear();
            this.router.navigate(['/']);
          }
        }
        return throwError(err);
      })
    )
  }
}
