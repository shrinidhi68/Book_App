import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { userEditPassword } from 'src/app/model/userinfo';
import { LoginService } from 'src/app/services/login.service';


@Component({
  selector: 'app-edituser',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.css'],
})
export class EdituserComponent implements OnInit {
  userPassChange = new userEditPassword();
  constructor(private lts: LoginService, private router: Router) {}

  ngOnInit(): void {}
  editPassword() {
    console.log(this.userPassChange);
    this.lts.changePassword(this.userPassChange).subscribe(
      (response) => {
        alert('Password Changed Successfully');
        this.router.navigate(['/login']);
      },
      (error) => {
        alert('Sorry, password change unsuccessful');
      }
    );
  }
}
