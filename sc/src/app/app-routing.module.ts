import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchComponent } from './search/search.component';
import { MagazinesComponent } from './magazines/magazines.component';
import { MagazineDetailsComponent } from './magazine-details/magazine-details.component';
import { LoginComponent } from './login/login.component';
import { EditionComponent } from './edition/edition.component';
import { TasksComponent } from './tasks/tasks.component';
import { TaskDetailsComponent } from './task-details/task-details.component';

const routes: Routes = [
  { path: '', redirectTo: '/magazines', pathMatch: 'full' },
  { path: 'search', component: SearchComponent },
  { path: 'magazines', component: MagazinesComponent },
  { path: 'magazineDetails/:magazineIssn', component: MagazineDetailsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'edition/:editionId', component: EditionComponent},
  { path: 'tasks', component: TasksComponent,
   children:[
     { path: ':taskId', component: TaskDetailsComponent},
  ]}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
