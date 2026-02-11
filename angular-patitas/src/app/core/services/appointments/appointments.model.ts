import { Features } from 'src/app/utils/models';

export class Appointment extends Features {
  clientName: string = null;
  petName: string = null;
  reason: string = null;
  date: string = null;
  time: string = null;
  status: string = null;
}
