import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class BooksdataService {
  constructor(private http: HttpClient) {}

  getRecomendedBookData() {
    let apiurl = 'http://localhost:9000/api/v3/books/recomended';
    return this.http.get(apiurl);
  }
  getSearchBookData(search: any) {
    let apiurl = `http://localhost:9000/api/v3/books/search?q=${search}`;
    return this.http.get(apiurl);
  }
  
}
