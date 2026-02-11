import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { FreyAlertModel, FreyAlertService } from 'freya/alert';
import { catchError, throwError } from 'rxjs';

export const responseInterceptor: HttpInterceptorFn = (req, next) => {
  const alertService = inject(FreyAlertService);
  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      const alert = new FreyAlertModel();
      alert.title = 'Error';
      alert.description = error.error?.message || 'Error inesperado';
      alert.typeHeader = 'error';
      alert.textConfirmButton = 'ACEPTAR';
      alert.showCancelButton = false;
      alertService.openAlert(alert).subscribe();
      return throwError(() => error);
    })
  );
};
