import { Component } from '@angular/core';
import { BooksdataService } from './booksdata.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Book-app-project';

  constructor() { }
  ngOnInit():void {
    
  }

}
