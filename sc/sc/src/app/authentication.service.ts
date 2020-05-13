import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Location } from '@angular/common';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private httpClient: HttpClient,
     private router: Router,
     private location : Location,
     ) { }

  login(dto){
    let x = this.httpClient.post('https://localhost:8090/login', dto,{responseType:'text'});
    console.log(x);
    x.subscribe(
      res => {
        console.log(res);
          
        localStorage.setItem('token', res);        
        this.location.back();
        alert("Success!");
      }
    );
  }
  loginTask(dto){
    return this.httpClient.post('https://localhost:8090/login', dto,{responseType:'text'});
  }

  logout(){
    localStorage.removeItem('token');
    window.location.reload();
  }

  getCurrentUser() : Observable<User>{
    if(localStorage.getItem('token')==null){
      return null;
    }
    else{
      return this.httpClient.get<User>('https://localhost:8090/api/authentication');
    }
  }
}
