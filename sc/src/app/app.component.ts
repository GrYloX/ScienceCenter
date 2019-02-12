import { Component } from '@angular/core';
import { AuthenticationService } from './authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'sc';

  constructor(
    private authenticationService : AuthenticationService
  ){}
 
  getCurrentUser() : Boolean{
    if(this.authenticationService.getCurrentUser()==null){
      return false;
    }
    else{
      return true;
    }
  }

  logout() : void{
    this.authenticationService.logout();
  }
}
