import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SearchComponent } from './search/search.component';
import { MagazinesComponent } from './magazines/magazines.component';
import { MagazineDetailsComponent } from './magazine-details/magazine-details.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS }    from '@angular/common/http';
import { EditionComponent } from './edition/edition.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthInterceptor } from './auth-interceptor';
import { AuthenticationService } from './authentication.service';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    MagazinesComponent,
    MagazineDetailsComponent,
    LoginComponent,
    EditionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    AuthenticationService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
