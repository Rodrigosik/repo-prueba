import { HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

export const requestInterceptor: HttpInterceptorFn = (req, next) => {
  const clonedReq = req.clone({
    setHeaders: {
      'Content-Type': 'application/json',
    },
  });

  return next(clonedReq).pipe(
    catchError(error => {
      return throwError(() => error);
    })
  );
};
