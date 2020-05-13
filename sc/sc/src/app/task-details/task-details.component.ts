import { Component, OnInit, ChangeDetectorRef  , ViewChild } from '@angular/core';
import { TaskService } from '../task.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TasksComponent } from '../tasks/tasks.component';
import { AuthenticationService } from '../authentication.service';
import { User } from '../user';
import { Application } from '../application';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-task-details',
  templateUrl: './task-details.component.html',
  styleUrls: ['./task-details.component.css']
})
export class TaskDetailsComponent implements OnInit {

  private formFieldsDto = null;
  private formFields = [];
  private processInstanceId = "";
  private enumValues = new Map<String,String>();
  private taskName = "";
  private scienceField = null;
  private filteredReviewers :Map<String,String>;
  private reviewsEditor = null;  
  private reviewsAutor = null;  

  @ViewChild('f') form: NgForm;
  application : Application;
  login : Boolean;
  watch : Boolean;
  register : Boolean;

  constructor(
    private taskService : TaskService,
    private route: ActivatedRoute,
    private tasksComponent: TasksComponent,
    private authentificationService : AuthenticationService,
    private http: HttpClient,
    private ref: ChangeDetectorRef,
    private router : Router,
    ) { }

  ngOnInit() {
    this.route.params.subscribe(
      params => {
        const taskId = this.route.snapshot.paramMap.get('taskId'); 
        console.log("tu sam")         
         this.application=null;
         this.login=false;
         this.watch=false;
         this.filteredReviewers=null;
         this.scienceField=null;
         this.reviewsEditor=null;
         this.reviewsAutor=null;
         this.register=false;
         if(taskId=='noTasks'){
          this.formFieldsDto=null;
         }
         else{
          this.getTask(taskId);
         }
         
      }); 
  }

  filter(){
    const temp = this.enumValues;
    this.enumValues = this.filteredReviewers;
    this.filteredReviewers = temp;
    this.ref.markForCheck();
  }


  getTask(taskId){
    console.log(taskId);
    if(taskId=='initiate'){
      this.taskService.initiate()
      .subscribe(formFieldsDto =>{
        this.formFieldsDto = formFieldsDto;
        this.setFormFields();
      });
    }
    else{
      this.taskService.getTask(taskId)
      .subscribe(formFieldsDto =>{
        this.formFieldsDto = formFieldsDto;
        this.setFormFields();
        if(this.taskName=='Odabir recenzenta'){
          this.taskService.getScienceField(taskId)
          .subscribe(res => {
            this.scienceField = res;
            this.filteredReviewers = this.clone(this.enumValues);
            for(const [key, value] of Object.entries(this.enumValues)){
              if(!value.includes(this.scienceField.name)){
                delete this.filteredReviewers[key];
              }
            }
          })
        }
        if(this.taskName=='Osnovna obrada rada' || this.taskName=='Pregled rada (PDF-a)' || this.taskName=='Recenzija rada' || this.taskName=='Razmatranje recenzija i rada, uz donosenje konacne odluke'){
          const taskId = this.route.snapshot.paramMap.get('taskId');          
          this.taskService.getApplication(taskId)
          .subscribe(res => {
            this.application=res;
            if(this.taskName=='Pregled rada (PDF-a)' || this.taskName=='Recenzija rada' || this.taskName=='Razmatranje recenzija i rada, uz donosenje konacne odluke'){
              this.watch=true;              
              if(this.taskName=='Razmatranje recenzija i rada, uz donosenje konacne odluke'){
                this.taskService.getReviews(taskId)
                .subscribe(res =>{
                  this.reviewsEditor = res;
                })
              }
            }
          });
        }
      });
    }       
  }

  setFormFields(){
    this.formFields = this.formFieldsDto.formFields;
    this.processInstanceId = this.formFieldsDto.processInstanceId;   
    this.taskName = this.formFieldsDto.taskName;     
    if(this.taskName=='Prijava na sistem'){
      this.login=true;
    }
    if(this.taskName=='Unos podataka'){
      this.register=true;
    }
    this.formFields.forEach( (field) =>{
          
      if( field.type.name=='enum'){
        this.enumValues = field.properties;
      }
      if( field.id=='answerToReviewers'){
        const taskId = this.route.snapshot.paramMap.get('taskId'); 
        this.taskService.getReviews(taskId)
                .subscribe(res =>{
                  this.reviewsAutor = res;
                })
      }
    });
  }

  onSubmit(value){
    let o = new Array();
    let user = new User();
    for (var property in value) {
      if(property!="upload"){
        console.log(property);
        if(this.login){
          if(property=="username"){
            user.username=value[property];
          }
          else{
            user.password=value[property];
          }
        } 
        console.log(value[property]);  
        if(property=="editDate" || property=="reviewDate" || property=="finalEditDate"){
          console.log(this.convertDate(value[property]));
          o.push({fieldId : property, fieldValue : this.convertDate(value[property])});
        }          
        else{
          o.push({fieldId : property, fieldValue : value[property]});
        }
        
      }      
    }

    console.log(o);
    let x = JSON.stringify(o);
    console.log(x);
    if(this.login){
      this.authentificationService.loginTask(user).subscribe(res => {
        localStorage.setItem('token', res);  
        this.taskService.submitTask(x, this.formFieldsDto.taskId).subscribe(x => {this.tasksComponent.getUserTasksAndRefresh();});
      });
    }
    else if(this.register){
      this.taskService.submitTask(x, this.formFieldsDto.taskId).subscribe(res => this.router.navigateByUrl('/tasks/'+res.url));
    }
    else{
      this.taskService.submitTask(x, this.formFieldsDto.taskId).subscribe(x => {this.tasksComponent.getUserTasksAndRefresh();});
    }
  }

  fileChange(event) {
    let fileList: FileList = event.target.files;
    if(fileList.length > 0) {
        let file: File = fileList[0];
        let formData:FormData = new FormData();
        formData.append('file', file, file.name);
        let httpHeaders = new HttpHeaders();
        /** In Angular 5, including the header Content-Type can invalidate your request */
        httpHeaders.append('Content-Type', 'multipart/form-data');
        httpHeaders.append('Accept', 'application/json');
        const httpOptions = {headers: httpHeaders};
        this.http.post(`https://localhost:8090/api/files`, formData, httpOptions)
            .subscribe(
                (data:any) => {if(this.taskName=='Unos podataka o radu'){
                  this.form.form.get('paper').setValue(data.url);
                }
                else{
                  this.form.form.get('editedWork').setValue(data.url);
                }               
              },
                error => console.log(error)
            )
    }
  }

  convertDate(inputFormat) {
    function pad(s) { return (s < 10) ? '0' + s : s; }
    var d = new Date(inputFormat);
    return [pad(d.getDate()), pad(d.getMonth()+1), d.getFullYear()].join('/');
  }

  registerSignal(){
    const taskId = this.formFieldsDto.taskId;
    this.taskService.registerSignal(taskId)
    .subscribe(res => {
      this.router.navigateByUrl('/tasks/'+res.taskId);
    })
  }


  clone(obj) {
    var copy;

    // Handle the 3 simple types, and null or undefined
    if (null == obj || "object" != typeof obj) return obj;

    // Handle Date
    if (obj instanceof Date) {
        copy = new Date();
        copy.setTime(obj.getTime());
        return copy;
    }

    // Handle Array
    if (obj instanceof Array) {
        copy = [];
        for (var i = 0, len = obj.length; i < len; i++) {
            copy[i] = this.clone(obj[i]);
        }
        return copy;
    }

    // Handle Object
    if (obj instanceof Object) {
        copy = {};
        for (var attr in obj) {
            if (obj.hasOwnProperty(attr)) copy[attr] = this.clone(obj[attr]);
        }
        return copy;
    }

    throw new Error("Unable to copy obj! Its type isn't supported.");
}


}
