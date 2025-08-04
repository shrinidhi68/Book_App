import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { NavbarComponent } from '../navbar/navbar.component';
import { userauth } from 'src/app/model/userauth';
import { userinfo } from 'src/app/model/userinfo';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  user = new userauth();

  message = '';
  constructor(private rls: LoginService, private router: Router) {}
  ngOnInit(): void {}

  loginUser() {
    this.rls.loginUserfromBackend(this.user).subscribe(
      (response) => {
        console.log(response);
        this.rls.loginUser(response.token);
        this.rls.loginUsers(response.email);
        this.router.navigate(['/dashboard']);
      },
      (error) => {
        console.log(error);
        this.message = 'Invalid Credentials Try Again';
        alert('Sorry, login unsuccesful');
      }
    );
  }
}
