import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { userEditPassword, userinfo } from 'src/app/model/userinfo';
import { Fav } from 'src/app/model/db';
import { userauth } from 'src/app/model/userauth';
import { Variable } from '@angular/compiler/src/render3/r3_ast';
import { HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private http: HttpClient) {}

  public loginUserfromBackend(user: userauth): Observable<any> {
    return this.http.post('http://localhost:9000/api/v1/login', user);
    
  }
  public registerUsersfromBackend(user: userinfo): Observable<any> {
    return this.http.post('http://localhost:9000/api/v1/user', user);
  }
  public addFavoritefromBackend(favorite:Fav,user:userauth): Observable<any> {
    return this.http.post('http://localhost:9000/api/v2/user/favorite/'+localStorage.getItem('email'), favorite);
  }
  public deleteFavoritefromBackend(favorite: string) {
  const params = new HttpParams().set('favorite', favorite);
  return this.http.delete(
    'http://localhost:9000/api/v2/user/' + localStorage.getItem('email'),
    { params }
  );
}
  public getFavoritefromBackend():Observable<any> {
    return this.http.get('http://localhost:9000/api/v2/user/favorites/'+localStorage.getItem('email'));
  }
  public changePassword(user: userEditPassword): Observable<any> {
    return this.http.get(
      'http://localhost:8081/edit-password?usr=' +
        user.userName +
        '&oldP=' +
        user.oldPassword +
        '&newP=' +
        user.newPassword
    );
  }
  loginUser(token: string) {
    localStorage.setItem('token', token);
    return true;
  }
  loginUsers(email: string) {
    localStorage.setItem('email', email);
    return true;
  }
  loginUrl(favUrl:string){
    localStorage.setItem('favUrl',JSON.stringify(favUrl));
    if (favUrl == undefined || favUrl == null || favUrl == '') {
      return false;
    } else {
      return true;
    }
  }
  isLoggedIn() {
    let token = localStorage.getItem('token');
    if (token == undefined || token == null || token == '') {
      return false;
    } else {
      return true;
    }
  }
  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('email');

    return true;
  }
}
