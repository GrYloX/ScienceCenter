import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { AuthenticationService } from '../authentication.service';
import { Task } from '../task';
import { TaskService } from '../task.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  currentUser : User;
  tasks : Task[];

  constructor(    
    private authenticationService : AuthenticationService,
    private taskService : TaskService,
    private router : Router,      
  ) { }

  ngOnInit() {
    this.getCurrentUser();
  }

  getCurrentUser(): any {
    if(localStorage.getItem('token')!=null){
      this.authenticationService.getCurrentUser()
      .subscribe(user =>{
       this.currentUser = user;
       this.getUserTasks(this.currentUser.username);
      });
    }
    else{
      this.currentUser=null;
    }
    
  }

  getUserTasks(username : String) {
    this.taskService.getUserTasks(username)
    .subscribe(tasks => {this.tasks = tasks;
      if(this.tasks.length>0){
        this.router.navigateByUrl('/tasks/'+this.tasks[0].taskId);
      }
      else{
        this.router.navigateByUrl('/tasks/noTasks');
      }
    });
  }

  getUserTasksAndRefresh() {
    console.log(this.currentUser);
    if(this.currentUser==null){
      this.getCurrentUser()
    }
    else{
      this.getUserTasks(this.currentUser.username);  
    }
  }
}
