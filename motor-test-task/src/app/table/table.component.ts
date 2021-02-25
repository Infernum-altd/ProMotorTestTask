import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {MatTable, MatTableDataSource} from '@angular/material/table';

import {ResizeEvent} from 'angular-resizable-element';
import {TableService} from '../services/table.service';
import {TableRecord} from '../models/table-record';
import {strict} from 'assert';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  @ViewChild(MatTable) table: MatTable<any>;
  searchValue = '';
  isAddFieldOpen = false;
  newRecord = '';
  displayedColumns = ['id', 'value', 'actions'];
  dataSource = new MatTableDataSource<TableRecord>([]);

  constructor(private tableService: TableService) {
  }

  onResizeEnd(event: ResizeEvent, columnName): void {
    if (event.edges.right) {
      const cssValue = event.rectangle.width + 'px';
      const columnElts = document.getElementsByClassName('mat-column-' + columnName);
      // tslint:disable-next-line:prefer-for-of
      for (let i = 0; i < columnElts.length; i++) {
        const currentEl = columnElts[i] as HTMLDivElement;
        currentEl.style.width = cssValue;
      }
    }
  }

  searchRecords(substring: strict): void {
    this.tableService.searchRecord(substring).subscribe(
      resp => {
        this.dataSource.data = resp;
      }
    );
  }

  editRecord(record: TableRecord): void {
    if (confirm('Are you sure to edit record?')) {
      this.tableService.updateRecord(record).subscribe();
    }
  }

  removeRecord(record: TableRecord): void {
    if (confirm('Are you sure to delete record?')) {
      this.tableService.deleteRecord(record).subscribe(
        resp => {
          if (resp === 'OK') {
            const index = this.dataSource.data.indexOf(record);
            this.dataSource.data.splice(index, 1);
            this.dataSource._updateChangeSubscription();
          }
        }
      );
    }
  }

  saveRecord(value: string): void {
    if (confirm('Are you sure to save record?')) {
      this.tableService.addRecord(new TableRecord(value)).subscribe();
      this.isAddFieldOpen = false;
      this.newRecord = '';
    }
  }

  ngOnInit(): void {
  }
}



