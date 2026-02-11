import { inject, Injectable } from '@angular/core';
import { FreyAlertModel, FreyAlertService } from 'freya/alert';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AlertService {
  private readonly alertService = inject(FreyAlertService);

  successAlert(title: string, description?: string): Observable<boolean> {
    const config = new FreyAlertModel();
    config.title = title;
    config.description = description;
    config.textConfirmButton = 'ACEPTAR';
    config.showCancelButton = false;
    return this.alertService.openAlert(config);
  }

  warningAlert(title: string, description?: string): Observable<boolean> {
    const alert = new FreyAlertModel();
    alert.typeHeader = 'warning';
    alert.textCancelButton = 'CANCELAR';
    alert.textConfirmButton = 'CONTINUAR';

    alert.title = title;
    alert.description = description;

    return this.alertService.openAlert(alert);
  }

  errorAlert(title: string, description?: string): Observable<boolean> {
    const config = new FreyAlertModel();
    config.title = title;
    config.description = description;
    config.typeHeader = 'error';
    config.textConfirmButton = 'ACEPTAR';
    config.showCancelButton = false;
    return this.alertService.openAlert(config);
  }

  infoAlert(title: string, description?: string): Observable<boolean> {
    const config = new FreyAlertModel();
    config.title = title;
    config.description = description;
    config.typeHeader = 'info';
    config.textConfirmButton = 'ACEPTAR';
    config.showCancelButton = false;
    return this.alertService.openAlert(config);
  }
}
