import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { FormGroup, FormControl } from '@angular/forms';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user : User;
  credentials = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(
    private authentificationService : AuthenticationService
  ) { }

  ngOnInit() {
  }

  login(){
    this.user = new User();
    this.user.username=this.credentials.get('username').value;
    this.user.password=this.credentials.get('password').value;
    console.log(this.user.username + ' ' + this.user.password);
    this.authentificationService.login(this.user);
  }


}
