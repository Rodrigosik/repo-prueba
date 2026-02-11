import {
  Component,
  computed,
  contentChild,
  effect,
  input,
  model,
  signal,
} from '@angular/core';
import { FreyPaginatorComponent } from 'freya/paginator';
import { FreyTableComponent } from 'freya/table';

@Component({
  selector: 'frey-table-manager',
  imports: [FreyPaginatorComponent],
  templateUrl: './table-manager.component.html',
  styleUrl: './table-manager.component.scss',
})
export class TableManagerComponent {
  tableComponent = contentChild(FreyTableComponent);
  dataSource = input<any[]>([]);
  itemsPerPage = model<number>(5);
  // placeholderToSearch = input('Buscar...');
  // labelToSearch = input('Buscar');
  // searchTerm = '';
  totalItems = computed(() => this.dataSource().length);
  currentPage = signal(1);

  constructor() {
    effect(() => {
      if (this.tableComponent()) {
        this.tableComponent().dataSource.set(this.dataSource());
        this.tableComponent().itemsPerPage.set(this.itemsPerPage());
        this.tableComponent().currentPage.set(this.currentPage());
      }
    });
  }
}
