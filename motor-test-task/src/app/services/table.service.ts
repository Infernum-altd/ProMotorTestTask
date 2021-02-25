import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TableRecord} from '../models/table-record';

@Injectable({
  providedIn: 'root'
})
export class TableService {
  private BASE_URL = 'http://localhost:8080/record/';
  private ADD_RECORD = `${this.BASE_URL}add`;
  private GET_RECORDS_BY_SUBSTRING = `${this.BASE_URL}get/`;
  private UPDATE_RECORD = `${this.BASE_URL}update`;
  private DELETE_RECORD = `${this.BASE_URL}delete`;

  constructor(private http: HttpClient) {
  }

  addRecord(record: TableRecord): Observable<any> {
    return this.http.post(this.ADD_RECORD, record);
  }

  searchRecord(substring: string): Observable<any> {
    return this.http.get(this.GET_RECORDS_BY_SUBSTRING + substring);
  }

  updateRecord(record: TableRecord): Observable<any> {
    return this.http.post(this.UPDATE_RECORD, record);
  }

  deleteRecord(record: TableRecord): Observable<any> {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      body: {
        id: record.id,
        value: record.value
      }
    };

    return this.http.delete(this.DELETE_RECORD, options);
  }
}
