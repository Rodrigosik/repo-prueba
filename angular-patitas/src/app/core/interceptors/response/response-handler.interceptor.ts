// import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
// import { inject } from '@angular/core';
// import { catchError, throwError } from 'rxjs';

// export const responseHandlerInterceptor: HttpInterceptorFn = (req, next) => {
//   const alertService = inject(AlertService);
//   return next(req).pipe(
//     catchError((error: HttpErrorResponse) => {
//       const alert = new AlertModel();
//       alert.title = 'Error en servicio';
//       alert.description = error.error?.message || 'Error inesperado';
//       alert.typeHeader = 'error';
//       alert.textConfirmButton = 'ACEPTAR';
//       alert.showCancelButton = false;
//       alertService.openAlert(alert).subscribe();

//       return throwError(() => error);
//     })
//   );
// };
