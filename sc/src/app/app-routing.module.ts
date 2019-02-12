import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchComponent } from './search/search.component';
import { MagazinesComponent } from './magazines/magazines.component';
import { MagazineDetailsComponent } from './magazine-details/magazine-details.component';
import { LoginComponent } from './login/login.component';
import { EditionComponent } from './edition/edition.component';

const routes: Routes = [
  { path: '', redirectTo: '/magazines', pathMatch: 'full' },
  { path: 'search', component: SearchComponent },
  { path: 'magazines', component: MagazinesComponent },
  { path: 'magazineDetails/:magazineIssn', component: MagazineDetailsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'edition/:editionId', component: EditionComponent},
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
