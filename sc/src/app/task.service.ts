import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class TaskService {  

  private processUrl = 'https://localhost:8090/process';

  constructor(
    private http: HttpClient
    ) {}

  initiate() : Observable<any> {
    return this.http.get(this.processUrl+'/initiate');
  }

  getUserTasks(username:String): Observable<any> {
      return this.http.get(this.processUrl+'/getUserTasks/'+username);
  }

  getTask(taskId:String): Observable<any> {
    return this.http.get(this.processUrl+'/getTask/'+taskId);
  }

  submitTask(o,taskId): Observable<any> {
    return this.http.post(this.processUrl+'/submitTask/'+taskId,o,httpOptions);
  }

  getApplication(taskId: string): Observable<any> {
    return this.http.get(this.processUrl+'/getApplication/'+taskId);
  }  

  getScienceField(taskId: string): Observable<any> {
    return this.http.get(this.processUrl+'/getScienceField/'+taskId);
  }  

  getReviews(taskId: string): Observable<any> {
    return this.http.get(this.processUrl+'/getReviews/'+taskId);
  } 

  registerSignal(taskId: string): Observable<any> {
    return this.http.get(this.processUrl+'/registerSignal/'+taskId);
  }   
}
