import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { BooksdataService } from 'src/app/booksdata.service';
import { Fav } from 'src/app/model/db';
import { userauth } from 'src/app/model/userauth';
import { LoginService } from 'src/app/services/login.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  searchValue = '';
  bookData: any;
  favBookData: Array<any>=[];
  arr1storing: Array<any> = [];

  constructor(private bookDataService: BooksdataService,private service: LoginService) {}
  ngOnInit(): void {
    localStorage.setItem('favorite', JSON.stringify([]));
   
    this.bookDataService.getRecomendedBookData().subscribe((data: any) => {
      const recomentData = data.works?.map((e: any) => {
        return {
          ...e,
          ['cover_edition_key']: e['cover_edition_key']
            ? `https://covers.openlibrary.org/b/olid/${e['cover_edition_key']}-L.jpg`
            : 'https://images-na.ssl-images-amazon.com/images/I/51EYWoYlA2S._SX413_BO1,204,203,200_.jpg',
        };
      });
      this.bookData = recomentData;
      console.log(this.bookData);
    });

    this.service.getFavoritefromBackend().subscribe(
      (response) => {
        console.log(response);
        this.favBookData=response
       
        }
       
      );
    
  }
      
   
  @Output()
  searchTextChanged: EventEmitter<string> = new EventEmitter<string>();
  searchTextChange() {
    this.searchTextChanged.emit(this.searchValue);
  }

  Fav :any;
  user = new userauth();
  addFavoriteData(url: any) {

    this.Fav=new Fav(JSON.stringify(url.favUrl));
 
    this.service.addFavoritefromBackend(this.Fav,this.user).subscribe(
      (response) => {
        console.log(response);
        
      },
      (error) => {
        console.log(error); 
       
      }
    );
  }
  removeFavoriteData(url: any) {
    
    
    this.service.deleteFavoritefromBackend(url).subscribe(
      (response) => {
        console.log(response);
        
      },
      (error) => {
        console.log(error);
       
      }
    );

  }

  search() {
    if (this.searchValue != '') {
      this.bookDataService.getSearchBookData(this.searchValue).subscribe(
        (res: any) => {
          const searchData = res.docs?.map((e: any) => {
            return {
              ...e,
              ['cover_edition_key']: e['cover_edition_key']
                ? `https://covers.openlibrary.org/b/olid/${e['cover_edition_key']}-L.jpg`
                : 'https://images-na.ssl-images-amazon.com/images/I/51EYWoYlA2S._SX413_BO1,204,203,200_.jpg',
            };
          });
          this.bookData = searchData;
          console.log(this.bookData);
        },
        (err) => {
          console.log(err);
        }
      );
    }
  }
}
