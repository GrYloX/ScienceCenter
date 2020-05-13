import { Injectable } from '@angular/core';
import { Edition } from './edition';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable} from 'rxjs';
import { Paper } from './paper';
import { MerchantOrder } from './merchantOrder';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class EditionService {

  

  private editionsUrl = 'https://localhost:8090/edition';
  private editionPapersUrl = 'https://localhost:8090/paper/getEditionPapers';
  private fileUrl = 'https://localhost:8090/api';

  constructor(
    private http: HttpClient,
  ) { }

  getEditions(): Observable<Edition[]> {
    return this.http.get<Edition[]>(this.editionsUrl+'/getEditions');
  }

  getEdition(editionId : String): Observable<Edition> {
      return this.http.get<Edition>(this.editionsUrl+'/'+editionId);
  }

  getEditionPapers(editionId): Observable<Paper[]>{
      return this.http.get<Paper[]>(this.editionPapersUrl+'/'+editionId);
  }

  redirectOrder(merchantOrder : MerchantOrder){
    return this.http.post('https://localhost:8090/merchantOrderi/sacuvajMerchantOrdera',merchantOrder, httpOptions) as Observable<any>;
  }

  downloadEdition(editionId : string){
    return this.http.get(this.fileUrl+'/downloadEdition/'+ editionId);
  }
  downloadPaper(paperFilename : string){    
    return this.http.get(this.fileUrl+'/files/'+ paperFilename), {responseType: 'arraybuffer'};
  }
}
