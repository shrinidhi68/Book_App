import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';
import { userinfo } from 'src/app/model/userinfo';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  message='';
  user=new userinfo();
  constructor(private rls:LoginService,private router:Router) { }

  ngOnInit(): void {
  }
  
  saveuser(){
    this.rls.registerUsersfromBackend(this.user).subscribe(
      response=>{
        console.log(response);
        this.message="Registered Successfully";
        this.router.navigate(['/login']);
        
      },
      error=>{
        console.log(error);
        this.message = 'Invalid Credentials Try Again';
        alert('Sorry, register unsuccesful');
      }
    )
  }
  
  
}
